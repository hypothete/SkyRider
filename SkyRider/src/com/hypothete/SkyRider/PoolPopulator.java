package com.hypothete.SkyRider;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PoolPopulator extends BlockPopulator {

	public void populate(World world, Random random, Chunk chunk) {
		
		int x, z, y;
		
		
		for(y=96; y < 224; y++){
			
			int grasscount = 0;
			
			for(x=0; x < 16; x++){
				for(z=0; z< 16; z++){
					
					if(chunk.getBlock(x, y, z).getType() == Material.GRASS){
						grasscount += 1;
					}					
				}
			}
			
			if(grasscount > 7){
				
				int xcheck = random.nextInt(16);
				int zcheck = random.nextInt(16);
				
				Material pondtype;
				
				if(random.nextInt(1000) > 995){
					pondtype = Material.LAVA;
				}
				else{
					pondtype = Material.WATER;
				}
				
				if(chunk.getBlock(xcheck, y, zcheck).getType() == Material.GRASS && random.nextDouble()*10.0 > 7.5){
					
						chunk.getBlock(xcheck, y, zcheck).setType(pondtype);
						chunk.getBlock(xcheck+random.nextInt(2)-1, y, zcheck+random.nextInt(2)-1).setType(pondtype);

					
				}
			}
			
		}
		
		
	}
}
