/*
 * Copyright (c) 2022-2023 See AUTHORS file.
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
import com.github.tommyettinger.digital.Interpolations;
import com.github.tommyettinger.random.*;
import com.github.tommyettinger.random.distribution.KumaraswamyDistribution;
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
    public void testVanDerCorputQuasiRandom() {
        Kryo kryo = new Kryo();
        kryo.register(VanDerCorputQuasiRandom.class, new VanDerCorputQuasiRandomSerializer());

        VanDerCorputQuasiRandom data = new VanDerCorputQuasiRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            VanDerCorputQuasiRandom data2 = kryo.readObject(input, VanDerCorputQuasiRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testGoldenQuasiRandom() {
        Kryo kryo = new Kryo();
        kryo.register(GoldenQuasiRandom.class, new GoldenQuasiRandomSerializer());

        GoldenQuasiRandom data = new GoldenQuasiRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            GoldenQuasiRandom data2 = kryo.readObject(input, GoldenQuasiRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testLowChangeQuasiRandom() {
        Kryo kryo = new Kryo();
        kryo.register(LowChangeQuasiRandom.class, new LowChangeQuasiRandomSerializer());

        LowChangeQuasiRandom data = new LowChangeQuasiRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LowChangeQuasiRandom data2 = kryo.readObject(input, LowChangeQuasiRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testTupleQuasiRandom() {
        Kryo kryo = new Kryo();
        kryo.register(TupleQuasiRandom.class, new TupleQuasiRandomSerializer());

        TupleQuasiRandom data = new TupleQuasiRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            TupleQuasiRandom data2 = kryo.readObject(input, TupleQuasiRandom.class);
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
    public void testFlowRandom() {
        Kryo kryo = new Kryo();
        kryo.register(FlowRandom.class, new FlowRandomSerializer());

        FlowRandom data = new FlowRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FlowRandom data2 = kryo.readObject(input, FlowRandom.class);
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
    public void testScruffRandom() {
        Kryo kryo = new Kryo();
        kryo.register(ScruffRandom.class, new ScruffRandomSerializer());

        ScruffRandom data = new ScruffRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ScruffRandom data2 = kryo.readObject(input, ScruffRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPouchRandom() {
        Kryo kryo = new Kryo();
        kryo.register(PouchRandom.class, new PouchRandomSerializer());

        PouchRandom data = new PouchRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PouchRandom data2 = kryo.readObject(input, PouchRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testSfc64Random() {
        Kryo kryo = new Kryo();
        kryo.register(Sfc64Random.class, new Sfc64RandomSerializer());

        Sfc64Random data = new Sfc64Random(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Sfc64Random data2 = kryo.readObject(input, Sfc64Random.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPasarRandom() {
        Kryo kryo = new Kryo();
        kryo.register(PasarRandom.class, new PasarRandomSerializer());

        PasarRandom data = new PasarRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PasarRandom data2 = kryo.readObject(input, PasarRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCrand64Random() {
        Kryo kryo = new Kryo();
        kryo.register(Crand64Random.class, new Crand64RandomSerializer());

        Crand64Random data = new Crand64Random(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Crand64Random data2 = kryo.readObject(input, Crand64Random.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testAceRandom() {
        Kryo kryo = new Kryo();
        kryo.register(AceRandom.class, new AceRandomSerializer());

        AceRandom data = new AceRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            AceRandom data2 = kryo.readObject(input, AceRandom.class);
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

    @Test
    public void testXoshiro256MX3Random() {
        Kryo kryo = new Kryo();
        kryo.register(Xoshiro256MX3Random.class, new Xoshiro256MX3RandomSerializer());

        Xoshiro256MX3Random data = new Xoshiro256MX3Random(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Xoshiro256MX3Random data2 = kryo.readObject(input, Xoshiro256MX3Random.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
 
    @Test
    public void testXoroshiro128StarStarRandom() {
        Kryo kryo = new Kryo();
        kryo.register(Xoroshiro128StarStarRandom.class, new Xoroshiro128StarStarRandomSerializer());

        Xoroshiro128StarStarRandom data = new Xoroshiro128StarStarRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Xoroshiro128StarStarRandom data2 = kryo.readObject(input, Xoroshiro128StarStarRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testChopRandom() {
        Kryo kryo = new Kryo();
        kryo.register(ChopRandom.class, new ChopRandomSerializer());

        ChopRandom data = new ChopRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ChopRandom data2 = kryo.readObject(input, ChopRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testJsf32Random() {
        Kryo kryo = new Kryo();
        kryo.register(Jsf32Random.class, new Jsf32RandomSerializer());

        Jsf32Random data = new Jsf32Random(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Jsf32Random data2 = kryo.readObject(input, Jsf32Random.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testXoshiro128PlusPlusRandom() {
        Kryo kryo = new Kryo();
        kryo.register(Xoshiro128PlusPlusRandom.class, new Xoshiro128PlusPlusRandomSerializer());

        Xoshiro128PlusPlusRandom data = new Xoshiro128PlusPlusRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Xoshiro128PlusPlusRandom data2 = kryo.readObject(input, Xoshiro128PlusPlusRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testInterpolatedRandom() {
        Kryo kryo = new Kryo();
//        InterpolatedRandomSerializer ser = new InterpolatedRandomSerializer();
        kryo.register(InterpolatedRandom.class, new InterpolatedRandomSerializer());

        InterpolatedRandom random = new InterpolatedRandom(Interpolations.kumaraswamyExtremeB,
                new DistinctRandom(123L));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, random);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            InterpolatedRandom data2 = kryo.readObject(input, InterpolatedRandom.class);
            Assert.assertEquals(random.nextDouble(), data2.nextDouble(), 0x1p-32);
            Assert.assertEquals(random.nextDouble(), data2.nextDouble(), 0x1p-32);
            Assert.assertTrue(EnhancedRandom.areEqual(random, data2));
        }
    }


    @Test
    public void testKnownSequenceRandom() {
        Kryo kryo = new Kryo();
        KnownSequenceRandomSerializer ser = new KnownSequenceRandomSerializer();
        kryo.register(KnownSequenceRandom.class, ser);

        KnownSequenceRandom data = new KnownSequenceRandom(LongSequence.with(0L, 1L, -2L, -3L, 4L, 5L));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            KnownSequenceRandom data2 = kryo.readObject(input, KnownSequenceRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testReverseWrapper() {
        Kryo kryo = new Kryo();
        ReverseWrapperSerializer ser = new ReverseWrapperSerializer();
        kryo.register(ReverseWrapper.class, ser);

        ReverseWrapper data = new ReverseWrapper(new DistinctRandom(-12345L));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ReverseWrapper data2 = kryo.readObject(input, ReverseWrapper.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testArchivalWrapper() {
        Kryo kryo = new Kryo();
        ArchivalWrapperSerializer ser = new ArchivalWrapperSerializer();
        kryo.register(ArchivalWrapper.class, ser);

        ArchivalWrapper data = new ArchivalWrapper(new DistinctRandom(-12345L));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ArchivalWrapper data2 = kryo.readObject(input, ArchivalWrapper.class);
//            System.out.println("data...");
//            System.out.println(data);
//            System.out.println("vs. data2...");
//            System.out.println(data2);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnhancedRandom() {
        Kryo kryo = new Kryo();
        EnhancedRandomSerializer ser = new EnhancedRandomSerializer();
        kryo.register(EnhancedRandom.class, ser);

        EnhancedRandom data = new Xoshiro128PlusPlusRandom(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnhancedRandom data2 = kryo.readObject(input, EnhancedRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongSequence() {
        Kryo kryo = new Kryo();
        kryo.register(LongSequence.class, new LongSequenceSerializer());

        LongSequence data = LongSequence.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongSequence data2 = kryo.readObject(input, LongSequence.class);
            Assert.assertEquals(data, data2);
        }
    }
}
