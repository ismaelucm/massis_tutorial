package com.massisframework.massis3.examples.simulation;

import com.massisframework.massis3.genetic.GeneticAl;
import com.massisframework.massis3.genetic.GeneticAlSetup;
import com.massisframework.massis3.genetic.Individual;
import com.massisframework.massis3.genetic.SelectionCommands.AbstractSelection;
import com.massisframework.massis3.genetic.SelectionCommands.Hierarchical;
import com.massisframework.massis3.genetic.SelectionCommands.Tournament;
import com.massisframework.massis3.genetic.cross.AbstractCross;
import com.massisframework.massis3.simulator.Configuration.ArquetypeSetDescription;
import com.massisframework.massis3.simulator.Configuration.SimulationDescription;
import com.massisframework.massis3.simulator.Core.Simulation;
import com.massisframework.massis3.simulator.Genetic.FindBestWorldConfiguration;
import com.massisframework.massis3.simulator.Genetic.PedestrianGene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class GeneticBehavior
{
    public static final String defaultArquetype = "/massis3-examples/src/main/resources/ArquetypeSimulationConfigExample.xml";
    public static final String defaulScene = "/massis3-examples/src/main/resources/SeneGraphExperiment.xml";
    public static final String dafaulSimulationResult = "/Entrada_Clase.txt";
    public static final String defaultSolutionFileOutput = "/Solution.txt";


    public static final int cst_defaultPathSize = 8;
    public static final AbstractCross.AbstractCrossMethods cst_defaultCrossMethod = AbstractCross.AbstractCrossMethods.UNIFORM_CROSS;
    public static final AbstractSelection.AbstractSelectionMethods cst_defaulSelectionMethod = AbstractSelection.AbstractSelectionMethods.TOURNAMENT;
    public static final float defaultHierarchicalAValue = 0.03030303f;
    public static final float defaultHierarchicalBValue = 0.0f;
    public static final int defaultTournamentSize = 10;

    public static int defaultElitism = 40;
    public static float defaultMutationRate = 0.5f;
    public static float defaultCrossoverRate = 0.5f;
    public static int defaultPopulationSize = 400;
    public static int defaultChromosomeLenght = 250;
    public static int defaultNumMaxGeneration = 2000;

    public static void main(String[] args)
    {

        String currentPath = MainUtils.getCurrentPath();
        if(args.length > 0)
        {
            //configuración por consola
        }

        AbstractSelection.AbstractSelectionMethods selectionMethod= cst_defaulSelectionMethod;
        AbstractCross.AbstractCrossMethods crossMethod= cst_defaultCrossMethod;
        int pathSize = cst_defaultPathSize;
        float hierarchicalAValue = defaultHierarchicalAValue;
        float hierarchicalBValue = defaultHierarchicalBValue;
        int torunementSize = defaultTournamentSize;


        int elitism = defaultElitism;
        float mutationRate = defaultMutationRate;
        float crossoverRate = defaultCrossoverRate;
        int populationSize = defaultPopulationSize;
        int chromosomeLenght = defaultChromosomeLenght;
        int numMaxGeneration = defaultNumMaxGeneration;

        Simulation simulation = CreateSimulation();
        String dataFile = currentPath+dafaulSimulationResult;
        String solutionFile = currentPath+defaultSolutionFileOutput;

        PedestrianGene.Init(simulation.getNodes());

        AbstractSelection<PedestrianGene> selection = AbstractSelection.getSelectionMethod(selectionMethod);
        if(selectionMethod == AbstractSelection.AbstractSelectionMethods.HIERARCHICAL)
            ((Hierarchical<PedestrianGene>)(selection)).setAdicionalConfiguration(hierarchicalAValue,hierarchicalBValue);
        else if(selectionMethod == AbstractSelection.AbstractSelectionMethods.TOURNAMENT)
            ((Tournament<PedestrianGene>)(selection)).setAdicionalConfiguration(torunementSize);

        AbstractCross<PedestrianGene> crossover = AbstractCross.getCrossMethod(crossMethod);
        //Simulation simulation, String dataToCompareFile,
        // AbstractSelection<PedestrianGene> selectionMethod,AbstractCross<PedestrianGene> crossMethod,
        // int pathSize
        FindBestWorldConfiguration problem = new FindBestWorldConfiguration(simulation,dataFile,selection, crossover,pathSize);


        //float _mutationRate, float _crossoverRate, int _populationSize, int chromosomeLenght, int _numMaxGeneratoin, int _elitism
        GeneticAlSetup setup = new GeneticAlSetup(mutationRate,crossoverRate,populationSize,chromosomeLenght,numMaxGeneration,elitism);

        GeneticAl<PedestrianGene> genenticAl = new GeneticAl<PedestrianGene>(problem,setup,solutionFile);


        try {

            boolean stop = false;
            genenticAl.init();
            do {

                genenticAl.run(true);

                System.out.println("maximum number of generations reached. Do you want to continue? Y/N");
                Scanner s = new Scanner(System.in);
                String line = s.nextLine();
                if(line.compareToIgnoreCase("y") == 0)
                {
                    int max = genenticAl.getMaxGenerations();
                    genenticAl.setMaxGenerations(max*2);
                }
                else
                    stop = true;
            }while(!stop);

            genenticAl.writeSolution();

        }
        catch(Exception e)
        {
            System.out.println("Error "+e.getMessage());
            e.printStackTrace();
        }

        Individual<PedestrianGene> theBest = genenticAl.getPopulation().getIndividual(0);

        System.out.println("The best "+theBest.getFitness());


    }

    public static Simulation CreateSimulation()
    {
        String currentPath = MainUtils.getCurrentPath();

        String arquetype    = currentPath+defaultArquetype;
        System.out.println("arquetype=>"+arquetype);
        String scene        = currentPath+defaulScene;
        System.out.println("scene=>"+scene);

        try {
            InputStream arquetypeStream = new FileInputStream(arquetype);
            InputStream sceneStream = new FileInputStream(scene);

            ArquetypeSetDescription arquetypeSetDescription = new ArquetypeSetDescription(arquetypeStream);

            SimulationDescription simDescription = new SimulationDescription(sceneStream);

            Simulation simulation = new Simulation();
            simulation.addArquetype(arquetypeSetDescription);
            simulation.configure(simDescription);

            return simulation;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
