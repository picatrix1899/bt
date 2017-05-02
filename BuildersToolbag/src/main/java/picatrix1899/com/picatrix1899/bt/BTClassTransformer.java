package com.picatrix1899.bt;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;

import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;



public class BTClassTransformer implements net.minecraft.launchwrapper.IClassTransformer {

	@Override
	public byte[] transform(String arg0, String arg1, byte[] arg2) {
		
		if (arg0.equals("arj")) {
			System.out.println("********* INSIDE OBFUSCATED EXPLOSION TRANSFORMER ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2);
        }
		
		if (arg0.equals("net.minecraft.block.BlockPane")) {
			System.out.println("********* INSIDE EXPLOSION TRANSFORMER ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, false);
        }
		
        return arg2;
	}
	
	public byte[] patchClassASM(String name, byte[] bytes) {
	 	String targetMethodName = "";
	 	
        targetMethodName ="b";
        

      //set up ASM class manipulation stuff. Consult the ASM docs for details
	    ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        
        
       
      //Now we loop over all of the methods declared inside the Explosion class until we get to the targetMethodName "doExplosionB"

        // find method to inject into
        Iterator<MethodNode> methods = classNode.methods.iterator();
        while(methods.hasNext())
        {
            MethodNode m = methods.next();
            System.out.println("********* Method Name: "+m.name + " Desc:" + m.desc);
            

            if ((m.name.equals(targetMethodName) && m.desc.equals("(Latl;Lajw;Lco;)Lbdt;")))
            {

                Iterator<AbstractInsnNode> iter = m.instructions.iterator();

                AbstractInsnNode[] del = new AbstractInsnNode[8];
                
                AbstractInsnNode returnStatement = m.instructions.get(14);
                
                int ind = 0;
                
                for(int i = 6; i <= 13; i++)
                {
                	del[ind] = m.instructions.get(i);
                	ind++;
                }
                
                for(AbstractInsnNode d : del)
                {
                	m.instructions.remove(d);
                }
                
                MethodInsnNode b = new MethodInsnNode(183, "alu","b","(Latl;Lajw;Lco;)Lbdt;", false);
                
                m.instructions.insertBefore(returnStatement, b);
            }
            
        }
              
	     //ASM specific for cleaning up and returning the final bytes for JVM processing.
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray(); 
	}
	
	public byte[] patchClassASM(String name, byte[] bytes, boolean obfuscated) {
	 	String targetMethodName = "";
        
        if(obfuscated == true)
        	targetMethodName ="i";
        else
        	targetMethodName ="getBoundingBox";
        

      //set up ASM class manipulation stuff. Consult the ASM docs for details
	    ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        
        
       
      //Now we loop over all of the methods declared inside the Explosion class until we get to the targetMethodName "doExplosionB"

        // find method to inject into
        Iterator<MethodNode> methods = classNode.methods.iterator();
        while(methods.hasNext())
        {
            MethodNode m = methods.next();
            System.out.println("********* Method Name: "+m.name + " Desc:" + m.desc);
            

            if ((m.name.equals(targetMethodName) && m.desc.equals("(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;")))
            {

                Iterator<AbstractInsnNode> iter = m.instructions.iterator();

                AbstractInsnNode[] del = new AbstractInsnNode[8];
                
                AbstractInsnNode returnStatement = m.instructions.get(14);
                
                int ind = 0;
                
                for(int i = 6; i <= 13; i++)
                {
                	del[ind] = m.instructions.get(i);
                	ind++;
                }
                
                for(AbstractInsnNode d : del)
                {
                	m.instructions.remove(d);
                }
                
                MethodInsnNode b = new MethodInsnNode(183, "net/minecraft/block/Block","getBoundingBox","(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;", false);
                
                m.instructions.insertBefore(returnStatement, b);
            }
            
        }
              
	     //ASM specific for cleaning up and returning the final bytes for JVM processing.
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray(); 
	}

}
