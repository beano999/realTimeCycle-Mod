package me.beanoW.realtimecycle;
//Beano999 | March 27th 2025
//Minecraft But the Time Reflects The Real World.

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTickManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiling.jfr.event.ServerTickTimeEvent;

import java.time.LocalTime;

public class Realtimecycle implements ModInitializer {
    private int tickCounter;
    private double tickPerMin = 16.6666667;
    private double tickPerSec = 0.27777778;

    @Override
    public void onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(this::updateTime);
    }

    private void updateTime(MinecraftServer server) {
        //tickCounter++;
        //tick counter may help to smooth the sky in game not sure yet
        //if (tickCounter == 1) {
            //tickCounter = 0;
            ServerWorld world = server.getOverworld(); // Get the main world
            int realWorldHour = LocalTime.now().getHour();  // Get current real-world hour
            int realWorldMinute = LocalTime.now().getMinute();
            int realWorldSecond = LocalTime.now().getSecond();

            // Convert IRL time to Minecraft time (0-24000 ticks per day)
            int minecraftTime = (int) ((realWorldHour * 1000) + (realWorldMinute * (tickPerMin) + (realWorldSecond * ((tickPerMin) / 60))) - 6000); // formula to convert irl time to mc time

            world.setTimeOfDay(minecraftTime);
        }

    }

