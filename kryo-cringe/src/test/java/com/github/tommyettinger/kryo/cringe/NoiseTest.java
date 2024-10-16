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

package com.github.tommyettinger.kryo.cringe;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class NoiseTest {

    @Test
    public void testCellularNoise() {
        Kryo kryo = new Kryo();
        CellularNoiseSerializer ser = new CellularNoiseSerializer();
        kryo.register(CellularNoise.class, ser);

        CellularNoise data = new CellularNoise(-12345, CellularNoise.NoiseType.DISTANCE_VALUE);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CellularNoise data2 = kryo.readObject(input, CellularNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testSimplexNoise() {
        Kryo kryo = new Kryo();
        SimplexNoiseSerializer ser = new SimplexNoiseSerializer();
        kryo.register(SimplexNoise.class, ser);

        SimplexNoise data = new SimplexNoise(-12345);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            SimplexNoise data2 = kryo.readObject(input, SimplexNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFoamNoise() {
        Kryo kryo = new Kryo();
        FoamNoiseSerializer ser = new FoamNoiseSerializer();
        kryo.register(FoamNoise.class, ser);

        FoamNoise data = new FoamNoise(-12345);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FoamNoise data2 = kryo.readObject(input, FoamNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testHoneyNoise() {
        Kryo kryo = new Kryo();
        HoneyNoiseSerializer ser = new HoneyNoiseSerializer();
        kryo.register(HoneyNoise.class, ser);

        HoneyNoise data = new HoneyNoise(-12345);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            HoneyNoise data2 = kryo.readObject(input, HoneyNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPerlinNoise() {
        Kryo kryo = new Kryo();
        PerlinNoiseSerializer ser = new PerlinNoiseSerializer();
        kryo.register(PerlinNoise.class, ser);

        PerlinNoise data = new PerlinNoise(-12345);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PerlinNoise data2 = kryo.readObject(input, PerlinNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testValueNoise() {
        Kryo kryo = new Kryo();
        ValueNoiseSerializer ser = new ValueNoiseSerializer();
        kryo.register(ValueNoise.class, ser);

        ValueNoise data = new ValueNoise(-12345);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ValueNoise data2 = kryo.readObject(input, ValueNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCyclicNoise() {
        Kryo kryo = new Kryo();
        CyclicNoiseSerializer ser = new CyclicNoiseSerializer();
        kryo.register(CyclicNoise.class, ser);

        CyclicNoise data = new CyclicNoise(-12345, 4, 1.4f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CyclicNoise data2 = kryo.readObject(input, CyclicNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testSorbetNoise() {
        Kryo kryo = new Kryo();
        SorbetNoiseSerializer ser = new SorbetNoiseSerializer();
        kryo.register(SorbetNoise.class, ser);

        SorbetNoise data = new SorbetNoise(-12345, 4, 1.4f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            SorbetNoise data2 = kryo.readObject(input, SorbetNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testRawNoise() {
        Kryo kryo = new Kryo();
        RawNoiseSerializer ser = new RawNoiseSerializer();
        kryo.register(RawNoise.class, ser);

        RawNoise data = new SimplexNoise(-12345);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RawNoise data2 = kryo.readObject(input, RawNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testContinuousNoise() {
        Kryo kryo = new Kryo();
        kryo.register(SimplexNoise.class, new SimplexNoiseSerializer());
        ContinuousNoiseSerializer ser = new ContinuousNoiseSerializer();
        kryo.register(ContinuousNoise.class, ser);

        ContinuousNoise data = new ContinuousNoise(new SimplexNoise(-12345), 1.4f, ContinuousNoise.RIDGED, 4);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ContinuousNoise data2 = kryo.readObject(input, ContinuousNoise.class);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f), data2.getNoise(1.1f, 2.2f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f), data2.getNoise(1.1f, 2.2f, -3.3f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f), 0.00001f);
            Assert.assertEquals(data.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), data2.getNoise(1.1f, 2.2f, -3.3f, -4.4f, 5.5f, 6.6f), 0.00001f);
            Assert.assertEquals(data, data2);
        }
    }
}
