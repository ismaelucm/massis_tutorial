# Creating new animations.

We can add new animations to be used by the human agents. For the system to work correctly, the animations have to be in **Biovision Hierarchy** format (BVH). 

The process has the following steps:

* Acquire the animation by downloading it from a repository or creating it yourself with a 3d editing program such as Blender, 3DS Max or Maya. You can also capture it with some motion capture system.

* Save the animation as a Biovision Hierarchy with the extensión ".bvh".

* The name of the file is used to name the animation. Use a descriptive name.
* Copy the animation file in massis3-assets/animations. In the folder Common if the animation is unisex. Otherwise, save it in the folder of the corresponding genre.
* Execute the script makehuman-build.sh to export the animation.

```bash
> sudo ./makehuman-build.sh
```