/*
 * Copyright (c) 2024 See AUTHORS file.
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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.*;
import com.github.tommyettinger.kryo.gdx.OldArraySerializer;
import com.github.tommyettinger.kryo.gdx.ArraySerializer;
import org.junit.Assert;
import org.junit.Test;

public class RandomTest {
    @Test
    public void testRandomDistinct64() {
        Kryo kryo = new Kryo();
        kryo.register(RandomDistinct64.class, new RandomDistinct64Serializer());

        RandomDistinct64 data = new RandomDistinct64(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomDistinct64 data2 = kryo.readObject(input, RandomDistinct64.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testRandomAce320() {
        Kryo kryo = new Kryo();
        kryo.register(RandomAce320.class, new RandomAce320Serializer());

        RandomAce320 data = new RandomAce320(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomAce320 data2 = kryo.readObject(input, RandomAce320.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }


    @Test
    public void testRandomChop128() {
        Kryo kryo = new Kryo();
        kryo.register(RandomChop128.class, new RandomChop128Serializer());

        RandomChop128 data = new RandomChop128(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomChop128 data2 = kryo.readObject(input, RandomChop128.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testRandomXMX256() {
        Kryo kryo = new Kryo();
        kryo.register(RandomXMX256.class, new RandomXMX256Serializer());

        RandomXMX256 data = new RandomXMX256(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomXMX256 data2 = kryo.readObject(input, RandomXMX256.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testGapShuffler() {
        Kryo kryo = new Kryo();
        kryo.register(RandomAce320.class, new RandomAce320Serializer());
        kryo.register(Array.class, new OldArraySerializer());
        kryo.register(GapShuffler.class, new GapShufflerSerializer());

        GapShuffler<String> data = new GapShuffler<>(new String[]{"Foo", "Bar", "Baz", "Quux"}, new RandomAce320(123));

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            GapShuffler<?> data2 = kryo.readObject(input, GapShuffler.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data, data2);
        }
    }

    /**
     * Length in bytes of 100000 Vector2 items: 900004
     */
    @Test
    public void testArrayVector2() {
        final int LIMIT = 100000;

        Kryo kryo = new Kryo();
        kryo.register(com.badlogic.gdx.math.Vector2.class);
        kryo.register(Array.class, new OldArraySerializer());
        RandomAce320 random = new RandomAce320(1234567890L);

        Array<Vector2> data = new Array<>(LIMIT);
        for (int i = 0; i < LIMIT; i++) {
            data.add(random.nextVector2(100f));
        }
        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Length in bytes of " + LIMIT + " Vector2 items: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Array<?> data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }

    /**
     * Length in bytes of 100000 String items: 2173740
     */
    @Test
    public void testArrayString() {
        final int LIMIT = 100000;

        Kryo kryo = new Kryo();
        kryo.register(Array.class, new OldArraySerializer());
        RandomAce320 random = new RandomAce320(1234567890L);

        Array<String> data = new Array<>(LIMIT);
        Vector2 vec = new Vector2();
        for (int i = 0; i < LIMIT; i++) {
            data.add(random.nextVector2InPlace(vec, 100f).toString());
        }
        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Length in bytes of " + LIMIT + " String items: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Array<?>data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }

    /**
     * Default settings:
     * Length in bytes of 100000 Vector2 items: 800005
     * With {@code ser.setElementClass(Vector2.class); ser.setElementsCanBeNull(false);}:
     * Length in bytes of 100000 Vector2 items: 800004
     */
    @Test
    public void testGdxArrayVector2() {
        final int LIMIT = 100000;

        Kryo kryo = new Kryo();
        kryo.register(com.badlogic.gdx.math.Vector2.class);
        ArraySerializer ser = new ArraySerializer();
        ser.setElementClass(Vector2.class); ser.setElementsCanBeNull(false);
        kryo.register(Array.class, ser);
        RandomAce320 random = new RandomAce320(1234567890L);

        Array<Vector2> data = new Array<>(LIMIT);
        for (int i = 0; i < LIMIT; i++) {
            data.add(random.nextVector2(100f));
        }
        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Length in bytes of " + LIMIT + " Vector2 items: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Array<?>data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }

    /**
     * Default settings:
     * Length in bytes of 100000 String items: 2073741
     * With {@code ser.setElementClass(String.class); ser.setElementsCanBeNull(false);}:
     * Length in bytes of 100000 String items: 2073740
     */
    @Test
    public void testGdxArrayString() {
        final int LIMIT = 100000;

        Kryo kryo = new Kryo();
        ArraySerializer ser = new ArraySerializer();
        ser.setElementClass(String.class); ser.setElementsCanBeNull(false);
        kryo.register(Array.class, ser);
        RandomAce320 random = new RandomAce320(1234567890L);

        Array<String> data = new Array<>(LIMIT);
        Vector2 vec = new Vector2();
        for (int i = 0; i < LIMIT; i++) {
            data.add(random.nextVector2InPlace(vec, 100f).toString());
        }
        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Length in bytes of " + LIMIT + " String items: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Array<?>data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }


    /**
     * Length in bytes of 100000 Vector2 items: 900010
     */
    @Test
    public void testArrayDefaultVector2() {
        final int LIMIT = 100000;

        Kryo kryo = new Kryo();
        kryo.register(com.badlogic.gdx.math.Vector2.class);
        kryo.register(java.lang.Object[].class);
        kryo.register(Array.class);
        RandomAce320 random = new RandomAce320(1234567890L);

        Array<Vector2> data = new Array<>(LIMIT);
        for (int i = 0; i < LIMIT; i++) {
            data.add(random.nextVector2(100f));
        }
        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Length in bytes of " + LIMIT + " Vector2 items: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Array<?>data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }

    /**
     * Length in bytes of 100000 String items: 2173746
     */
    @Test
    public void testArrayDefaultString() {
        final int LIMIT = 100000;

        Kryo kryo = new Kryo();
        kryo.register(java.lang.Object[].class);
        kryo.register(Array.class);
        RandomAce320 random = new RandomAce320(1234567890L);

        Array<String> data = new Array<>(LIMIT);
        Vector2 vec = new Vector2();
        for (int i = 0; i < LIMIT; i++) {
            data.add(random.nextVector2InPlace(vec, 100f).toString());
        }
        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Length in bytes of " + LIMIT + " String items: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Array<?>data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testWeightedTable() {
        Kryo kryo = new Kryo();
        kryo.register(RandomAce320.class, new RandomAce320Serializer());
        kryo.register(WeightedTable.class, new WeightedTableSerializer());

        WeightedTable data = new WeightedTable(new RandomAce320(123), 1.1f, 2.2f, 3.3f, 4.4f, 5.5f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            WeightedTable data2 = kryo.readObject(input, WeightedTable.class);
            int latest;
            Assert.assertEquals(data.random(0L), latest = data2.random(0L)); System.out.println(latest);
            Assert.assertEquals(data.random(1L), latest = data2.random(1L)); System.out.println(latest);
            Assert.assertEquals(data.random(2L), latest = data2.random(2L)); System.out.println(latest);
            Assert.assertEquals(data.stringSerialize(), data2.stringSerialize());
            Assert.assertEquals(data, data2);
        }
    }

}
