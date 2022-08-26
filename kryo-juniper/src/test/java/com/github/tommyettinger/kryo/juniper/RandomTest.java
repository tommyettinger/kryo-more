/*
 * Copyright (c) 2022 See AUTHORS file.
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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class RandomTest {
    @Test
    public void testDistinctRandom() {
        Kryo kryo = new Kryo();
        kryo.register(DistinctRandom.class, new DistinctRandomSerializer());

        DistinctRandom data = new DistinctRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            DistinctRandom data2 = kryo.readObject(input, DistinctRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testLaserRandom() {
        Kryo kryo = new Kryo();
        kryo.register(LaserRandom.class, new LaserRandomSerializer());

        LaserRandom data = new LaserRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LaserRandom data2 = kryo.readObject(input, LaserRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testMizuchiRandom() {
        Kryo kryo = new Kryo();
        kryo.register(MizuchiRandom.class, new MizuchiRandomSerializer());

        MizuchiRandom data = new MizuchiRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            MizuchiRandom data2 = kryo.readObject(input, MizuchiRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testRomuTrioRandom() {
        Kryo kryo = new Kryo();
        kryo.register(RomuTrioRandom.class, new RomuTrioRandomSerializer());

        RomuTrioRandom data = new RomuTrioRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RomuTrioRandom data2 = kryo.readObject(input, RomuTrioRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testTricycleRandom() {
        Kryo kryo = new Kryo();
        kryo.register(TricycleRandom.class, new TricycleRandomSerializer());

        TricycleRandom data = new TricycleRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            TricycleRandom data2 = kryo.readObject(input, TricycleRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testFourWheelRandom() {
        Kryo kryo = new Kryo();
        kryo.register(FourWheelRandom.class, new FourWheelRandomSerializer());

        FourWheelRandom data = new FourWheelRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FourWheelRandom data2 = kryo.readObject(input, FourWheelRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testStrangerRandom() {
        Kryo kryo = new Kryo();
        kryo.register(StrangerRandom.class, new StrangerRandomSerializer());

        StrangerRandom data = new StrangerRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            StrangerRandom data2 = kryo.readObject(input, StrangerRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testTrimRandom() {
        Kryo kryo = new Kryo();
        kryo.register(TrimRandom.class, new TrimRandomSerializer());

        TrimRandom data = new TrimRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            TrimRandom data2 = kryo.readObject(input, TrimRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testWhiskerRandom() {
        Kryo kryo = new Kryo();
        kryo.register(WhiskerRandom.class, new WhiskerRandomSerializer());

        WhiskerRandom data = new WhiskerRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            WhiskerRandom data2 = kryo.readObject(input, WhiskerRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testXoshiro256StarStarRandom() {
        Kryo kryo = new Kryo();
        kryo.register(Xoshiro256StarStarRandom.class, new Xoshiro256StarStarRandomSerializer());

        Xoshiro256StarStarRandom data = new Xoshiro256StarStarRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Xoshiro256StarStarRandom data2 = kryo.readObject(input, Xoshiro256StarStarRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
}
