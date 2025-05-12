/*
 * Copyright (c) 2022-2024 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.gand;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gand.GradientGridI2;
import com.github.tommyettinger.gand.Path;
import com.github.tommyettinger.gand.ds.IntList;
import com.github.tommyettinger.gand.utils.GridMetric;
import com.github.tommyettinger.gdcrux.PointI2;

/**
 * Kryo {@link Serializer} for gand {@link GradientGridI2}s.
 * You must have {@link PointI2} registered to use this as the serializer for GradientGridI2,
 * such as with {@link com.github.tommyettinger.kryo.gdcrux.PointI2Serializer}.
 */
public class GradientGridI2Serializer extends Serializer<GradientGridI2> {

    public GradientGridI2Serializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final GradientGridI2 data) {
        output.writeVarInt(data.getMeasurement().ordinal(), true);
        int width = data.getWidth();
        int height = data.getHeight();
        if(data.physicalMap != null && data.gradientMap != null) {
            output.writeVarInt(width, true);
            output.writeVarInt(height, true);
        }
        else {
            output.writeVarInt(0, true);
            output.writeVarInt(0, true);
        }
        output.writeVarInt(data.getBlockingRequirement(), true);

        IntList goals = data.getGoals();
        output.writeVarInt(goals.size(), true);
        for (int i = 0, n = goals.size(); i < n; i++) {
            output.writeVarInt(goals.get(i), true);
        }

        Path<PointI2> path = data.path;
        output.writeVarInt(path.size(), true);
        for (int i = 0, n = path.size(); i < n; i++) {
            output.writeVarInt(path.get(i).xi(), true);
            output.writeVarInt(path.get(i).yi(), true);
        }

        if(data.physicalMap != null && data.gradientMap != null) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    output.writeFloat(data.physicalMap[x][y]);
                }
            }
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    output.writeFloat(data.gradientMap[x][y]);
                }
            }
        }
    }

    /*
    @Override
    public void read(Json json, JsonValue jsonData) {
        GridMetric m = GridMetric.ALL[Math.min(Math.max(jsonData.getInt("m", 2), 0), 2)];
        setMeasurement(m);
        int w = Math.max(0, jsonData.getInt("w"));
        float[][] pm = new float[w][];

        setBlockingRequirement(jsonData.getInt("b"));
        goals.clear();
        goals.addAll(jsonData.get("g").asIntArray());
        path.clear();
        int[] p = jsonData.get("p").asIntArray();
        for (int i = 0; i < p.length; i += 2) {
            path.add(acquire(p[i], p[i+1]));
        }

        JsonValue a2 = jsonData.get("pm");
        if(a2 == null || a2.isNull()){
            initialized = false;
            physicalMap = null;
            gradientMap = null;
            return;
        }
        int x = 0;
        for(JsonValue sub = a2.child; sub != null; sub = sub.next){
            pm[x] = sub.asFloatArray();
            ++x;
        }
        initialize(pm);
        a2 = jsonData.get("gm");
        if(a2 == null || a2.isNull()){
            initialized = false;
            physicalMap = null;
            gradientMap = null;
            return;
        }
        x = 0;
        for(JsonValue sub = a2.child; sub != null; sub = sub.next){
            gradientMap[x] = sub.asFloatArray();
            ++x;
        }
    }
*/

    @Override
    public GradientGridI2 read(final Kryo kryo, final Input input, final Class<? extends GradientGridI2> dataClass) {
        GridMetric m = GridMetric.ALL[Math.min(Math.max(input.readVarInt(true), 0), 2)];
        int width = input.readVarInt(true);
        int height = input.readVarInt(true);
        int blocking = input.readVarInt(true);
        int goalSize = input.readVarInt(true);
        IntList goals = new IntList(goalSize);
        for (int i = 0; i < goalSize; i++) {
            goals.add(input.readVarInt(true));
        }
        int pathSize = input.readVarInt(true);
        Path<PointI2> path = new Path<>(pathSize);
        for (int i = 0; i < pathSize; i++) {
            path.add(new PointI2(input.readVarInt(true), input.readVarInt(true)));
        }
        GradientGridI2 gg;
        if(width > 0 && height > 0) {
            float[][] level = new float[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    level[x][y] = input.readFloat();
                }
            }
            gg = new GradientGridI2(level, m);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    gg.gradientMap[x][y] = input.readFloat();
                }
            }
        }
        else {
            gg = new GradientGridI2();
            gg.setMeasurement(m);
        }
        gg.setBlockingRequirement(blocking);
        gg.goals.addAll(goals);
        gg.path.addAll(path);

        return gg;
    }

    @Override
    public GradientGridI2 copy(Kryo kryo, GradientGridI2 original) {
        GradientGridI2 gg;
        if(original.physicalMap != null && original.gradientMap != null) {
            gg = new GradientGridI2(original.physicalMap, original.getMeasurement());
            for (int x = 0, w = gg.getWidth(), h = gg.getHeight(); x < w; x++) {
                System.arraycopy(original.gradientMap[x], 0, gg.physicalMap[x], 0, h);
            }
        } else {
          gg = new GradientGridI2();
          gg.setMeasurement(original.getMeasurement());
        }
        gg.setBlockingRequirement(original.getBlockingRequirement());
        gg.goals.addAll(original.goals);
        gg.path.addAll(original.path);
        return gg;
    }
}