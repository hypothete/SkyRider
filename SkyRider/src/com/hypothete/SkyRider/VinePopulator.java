package com.hypothete.SkyRider;


import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;


import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class VinePopulator extends BlockPopulator {
	
	
	public void populate(World world, Random random, Chunk chunk) {
		
		int x, z, y;
		for(x=0; x<16; ++x){
			for(z=0; z<16; ++z){
				for(y=96; y<225; y++){
					
					if(random.nextInt(100) > 90 && chunk.getBlock(x, y, z).getType() == Material.AIR && chunk.getBlock(x, y+1, z).getType() == Material.ENDER_STONE ){
						
						
								chunk.getBlock(x, y, z).setType(Material.VINE);
							
						
							

					}
					

				}
			}
	
		}
		
	}
}