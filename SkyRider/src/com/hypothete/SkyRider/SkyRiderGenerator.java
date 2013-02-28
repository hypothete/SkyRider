package com.hypothete.SkyRider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class SkyRiderGenerator extends ChunkGenerator{
	
	
	public List<BlockPopulator> getDefaultPopulators(World world){
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();

		populators.add(new PoolPopulator());
		populators.add(new FoliagePopulator());
		populators.add(new Monoliths());
		populators.add(new VinePopulator());
		

		return populators;
	}
	
	public Location getFixedSpawnLocation(World world, Random random){
		return new Location(world, 0, 128, 0);
	}
	
	//borrowing this from jtjj222's experiments w/ 3d noise. thanks!
	void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
		if (chunk[y>>4] == null) chunk[y>>4] = new byte[16*16*16];
		if (!(y<=256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0)) return; //Out of bounds
		try {
			chunk[y>>4][((y & 0xF) << 8) | (z << 4) | x] = (byte)material.getId();
		}
		catch (Exception e) {
		//do nothing
			}
	}
	
	public byte[][] generateBlockSections(World world, Random random, int ChunkX, int ChunkZ, BiomeGrid biomes){
		byte[][] blocks = new byte[world.getMaxHeight() / 16][];
		int x, y, z;
		
		Random rand = new Random(world.getSeed());
		
		SimplexOctaveGenerator octave = new SimplexOctaveGenerator(rand, 12);
		SimplexOctaveGenerator octave2 = new SimplexOctaveGenerator(rand, 8);
		SimplexOctaveGenerator octave3 = new SimplexOctaveGenerator(rand, 4);
		//SimplexOctaveGenerator octaveground = new SimplexOctaveGenerator(rand, 16);
		//SimplexOctaveGenerator octaveground2 = new SimplexOctaveGenerator(rand, 8);
		octave.setScale(1/48.0);
		octave2.setScale(1/8.0);
		octave3.setScale(1/64.0);
		//octaveground.setScale(1/32.0);
		//octaveground2.setScale(1/8.0);
		for(x=0; x < 16; x++){
			for(z=0; z<16; z++){
				
				biomes.setBiome(x, z, Biome.SKY);
				
				int real_x = x+ChunkX * 16;
				int real_z = z+ChunkZ*16;
				
				//setBlock(x, 2, z, blocks, Material.BEDROCK);
				
				/*double groundheight = Math.max(octaveground.noise(real_x, real_z, 0.5, 0.5),octaveground2.noise(real_x, real_z, 0.5, 0.5)) * 8 + 32;
				
				for(y=3; y< groundheight; y++){
					
					setBlock(x, y, z, blocks, Material.STONE);

				}*/
				 
				
				for(y=96; y<224; y++){
					
					double threshold =  0.98;
					double threshold2 = 0.99991;
					double threshold3 = 0.99999;
					//for some reason this breaks Multiverse2
					/*if(y < 96 || y > 224){
						threshold2 = .99999;
						threshold = 1;
					}*/
					
					double noise = octave.noise(real_x, y*2, real_z, 0.5, 0.5);
					double noise2 = octave2.noise(real_x, y/2, real_z, 0.5, 0.5);
					double noise3 = octave3.noise(real_x, y*2.1, real_z, 0.5, 0.5);
					if(noise > threshold || noise + noise2 > threshold2*2 || noise3 > threshold3){
						setBlock(x, y, z, blocks, Material.ENDER_STONE);
						setBlock(x, y+1, z, blocks, Material.DIRT);
						setBlock(x, y+2, z, blocks, Material.GRASS);
					}
				}
				
			}
			
		}
		
		return blocks;
	}
}
