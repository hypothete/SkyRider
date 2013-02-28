package com.hypothete.SkyRider;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;


import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class Monoliths extends BlockPopulator {
	
	
	public void populate(World world, Random random, Chunk chunk) {
		
		int x, z, y;
		for(x=0; x<16; ++x){
			for(z=0; z<16; ++z){
				for(y=96; y<225; y++){
					
					if(random.nextInt(100) > 98 && chunk.getBlock(x, y, z).getType() == Material.GRASS && chunk.getBlock(x, y, z + 2).getType() == Material.GRASS){
						
						for(int i=0; i<9; i++){
							for(int j=0; j<4; j++){
								chunk.getBlock(x+j, y+i, z).setType(Material.STONE);
								if(random.nextInt(20) > 18){
									chunk.getBlock(x+j, y+i, z).setType(Material.WOOL);
									chunk.getBlock(x+j, y+i, z).setData((byte) 0x8);
								}
								if(random.nextInt(30) > 27){
									chunk.getBlock(x+j, y+i, z).setType(Material.IRON_ORE);
								}
								if(random.nextInt(30) > 27){
									chunk.getBlock(x+j, y+i, z).setType(Material.GOLD_ORE);
								}
								if(random.nextInt(30) > 27){
									chunk.getBlock(x+j, y+i, z).setType(Material.REDSTONE_ORE);
								}
							}
						}
							

					}
					

				}
			}
	
		}
		
	}
}