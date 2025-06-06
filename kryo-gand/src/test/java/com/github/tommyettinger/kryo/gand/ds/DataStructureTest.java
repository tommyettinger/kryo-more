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

package com.github.tommyettinger.kryo.gand.ds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gand.ds.*;
import org.junit.Assert;
import org.junit.Test;

public class DataStructureTest {
    @Test
    public void testIntList() {
        Kryo kryo = new Kryo();
        kryo.register(IntList.class, new IntListSerializer());

        IntList data = IntList.with(-123, 0, 456, 0, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntList data2 = kryo.readObject(input, IntList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntDeque() {
        Kryo kryo = new Kryo();
        kryo.register(IntDeque.class, new IntDequeSerializer());

        IntDeque data = IntDeque.with(-123, 0, 456, 0, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntDeque data2 = kryo.readObject(input, IntDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectDeque() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectDeque.class, new ObjectDequeSerializer());

        ObjectDeque<String> data = ObjectDeque.with("-123.123", "0", "Four-Fifty Six", "0", "1.0", "-1.0", "0.000001");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectDeque data2 = kryo.readObject(input, ObjectDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjectSet.class, new ObjectSetSerializer());

        ObjectSet<String> data = ObjectSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectSet<?> data2 = kryo.readObject(input, ObjectSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjectOrderedSet.class, new ObjectOrderedSetSerializer());

        ObjectOrderedSet<String> data = ObjectOrderedSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectOrderedSet<?> data2 = kryo.readObject(input, ObjectOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
}
