package com.hypothete.SkyRider;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class FoliagePopulator extends BlockPopulator {

	public void populate(World world, Random random, Chunk chunk) {
		
		int x, z, y;
		for(x=0; x<16; ++x){
			for(z=0; z<16; ++z){
				for(y=96; y<225; y++){
					
					if(random.nextInt(100) > 80 && chunk.getBlock(x, y, z).getType() == Material.GRASS){
							chunk.getBlock(x, y+1, z).setType(Material.LONG_GRASS);
							chunk.getBlock(x, y+1, z).setData((byte)1);
					}
					
					if(random.nextInt(100) > 95 && chunk.getBlock(x, y, z).getType() == Material.GRASS){
						int chunkX = chunk.getX();
						int chunkZ = chunk.getZ();
						int realX = x + chunkX * 16;
						int realZ = z + chunkZ * 16;
						
						Location loc = new Location(world, (double)realX, (double)y+1, (double)realZ);
						world.generateTree(loc, TreeType.BIRCH);
					}
				}
			}
	
		}
		
	}
}
