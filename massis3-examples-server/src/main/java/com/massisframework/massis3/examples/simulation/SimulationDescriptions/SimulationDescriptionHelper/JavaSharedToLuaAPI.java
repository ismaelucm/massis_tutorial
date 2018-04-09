package com.massisframework.massis3.examples.simulation.SimulationDescriptions.SimulationDescriptionHelper;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;


/**
 * Library that can be called via LUAJ require()
 * this library, when loaded, creates a lua package called
 */

public class JavaSharedToLuaAPI
{
    static public class Create extends ThreeArgFunction{

        @Override
        public LuaValue call(LuaValue populationId, LuaValue num, LuaValue position) {
            System.out.println("Se ha ejecutado el comando Create con los argumentos:");
            System.out.println("populationId:"+populationId.toString());
            System.out.println("num:"+num.toint());
            System.out.println("position:"+num.toString());
            return NIL;
        }
    }
}
