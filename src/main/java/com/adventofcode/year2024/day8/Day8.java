package com.adventofcode.year2024.day8;

import java.util.*;

public class Day8 {

    private record Location(int x, int y) {}

    private record Antenna(Location location, char frequency) {}
    //private record Antinode(Antenna antenna1, Antenna antenna2, Location antinode1, Location antinode2) {}
    // antennas by location

    private final Map<Location, Antenna> locations = new HashMap<>();
    // antennas by frequency

    private final Map<Character, Set<Antenna>> antennasByFrequency = new HashMap<>();
    // antinode locations

    private final Set<Location> antinodeLocations = new HashSet<>();
    private final String input;

    private final Location mapSize;

    public Day8(String input) {
        this(input, false);
    }

    public Day8(String input, boolean enableNewAntinodeRules) {
        this.input = input;
        mapSize = createMapOfCity(input);
        determineAntinodes(enableNewAntinodeRules);
    }

    private Location createMapOfCity(String input) {
        Location loc = null;
        String[] inputLines = input.split("\\r?\\n");
        for (int y = 0; y < inputLines.length; y++) {
            for (int x = 0; x < inputLines[y].length(); x++) {
                char freq = inputLines[y].charAt(x);
                loc = new Location(x, y);
                if ((freq >= '0' && freq <= '9') ||
                        (freq >= 'A' && freq <= 'Z') ||
                        (freq >= 'a' && freq <= 'z')) {
                    Antenna ant = new Antenna(loc, freq);

                    locations.put(loc, ant);
                    Set<Antenna> antennasOfFrequency = antennasByFrequency.getOrDefault(freq, new HashSet<>());
                    antennasOfFrequency.add(ant);
                    antennasByFrequency.put(freq, antennasOfFrequency);
                }
            }
        }

        return loc;
    }

    private void determineAntinodes(boolean enableNewAntinodeRules) {
        // compare antennas with the same frequency e.g. all A's or all a's
        // work out the locations of the antinodes between each unique pairing

        Set<Character> antennaFrequencies = antennasByFrequency.keySet();
        for (Character freq : antennaFrequencies) {
            Set<Antenna> antennas = antennasByFrequency.get(freq);

            // compare each to each other and work out the antinode locations
            Iterator<Antenna> antenna1Iterator = antennas.iterator();
            while (antenna1Iterator.hasNext()) {
                Antenna antenna1 = antenna1Iterator.next();
                Iterator<Antenna> antenna2Iterator = antennas.iterator();
                while (antenna2Iterator.hasNext()) {
                    Antenna antenna2 = antenna2Iterator.next();
                    if (antenna1.equals(antenna2)) {
                        break;
                    }

                    // As there's more than one antenna of this type, they are both antinodes
                    if (enableNewAntinodeRules) {
                        antinodeLocations.add(antenna1.location);
                        antinodeLocations.add(antenna2.location);
                    }

                    int x1Diff = antenna1.location.x - antenna2.location.x;
                    int y1Diff = antenna1.location.y - antenna2.location.y;

                    int x2Diff = antenna2.location.x - antenna1.location.x;
                    int y2Diff = antenna2.location.y - antenna1.location.y;

                    int x1 = antenna1.location.x + (x1Diff);
                    int y1 = antenna1.location.y + (y1Diff);
                    Location loc1 = new Location(x1, y1);
                    while (loc1.x >= 0 && loc1.y >= 0 && mapSize.x >= loc1.x && mapSize.y >= loc1.y) {
                        antinodeLocations.add(loc1);
                        if (!enableNewAntinodeRules) {
                            break;
                        }
                        x1 = x1 + (x1Diff);
                        y1 = y1 + (y1Diff);
                        loc1 = new Location(x1, y1);
                    }

                    int x2 = antenna2.location.x + (x2Diff);
                    int y2 = antenna2.location.y + (y2Diff);
                    Location loc2 = new Location(x2, y2);
                    while (loc2.x >= 0 && loc2.y >= 0 && mapSize.x >= loc2.x && mapSize.y >= loc2.y) {
                        antinodeLocations.add(loc2);
                        if (!enableNewAntinodeRules) {
                            break;
                        }
                        x2 = x2 + (x2Diff);
                        y2 = y2 + (y2Diff);
                        loc2 = new Location(x2, y2);
                    }
                }
            }
        }
    }

    public int countAntinodes() {
        return antinodeLocations.size();
    }

    public String printMap() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y <= mapSize.y; y++) {
            for (int x = 0; x <= mapSize.x; x++) {
                Location curr = new Location(x, y);
                Antenna possibleAntenna = locations.get(curr);
                if (null != possibleAntenna) {
                    sb.append(possibleAntenna.frequency);
                } else if (antinodeLocations.contains(curr)) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            if (y < mapSize.y) {
                sb.append('\n');
            }
        }

        return sb.toString();
    }
}
