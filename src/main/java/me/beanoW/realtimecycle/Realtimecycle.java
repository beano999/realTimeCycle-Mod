package me.beanoW.realtimecycle;
//Beano999 | March 27th 2025
//Minecraft But the Time Reflects The Real World.

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import java.time.LocalTime;

public class Realtimecycle implements ModInitializer {
    private final double tickPerMin = 16.6666667;
    private final double tickPerSec = 0.27777778;

    @Override
    public void onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(this::updateTime);
    }

    private void updateTime(MinecraftServer server) {
            var localTime = LocalTime.now();

        ServerWorld world = server.getOverworld(); // Get the main world
            int realWorldHour = localTime.getHour();  // Get current real-world hour
            int realWorldMinute = localTime.getMinute();
            int realWorldSecond = localTime.getSecond();

            // Convert IRL time to Minecraft time (0-24000 ticks per day)
            int minecraftTime = (int) ((realWorldHour * 1000) + (realWorldMinute * (tickPerMin) + (realWorldSecond * (tickPerSec)) - 6000)); // formula to convert irl time to mc time

            world.setTimeOfDay(minecraftTime);
        }

    }

