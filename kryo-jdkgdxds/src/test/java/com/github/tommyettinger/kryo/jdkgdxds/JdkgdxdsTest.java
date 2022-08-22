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

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.ds.IntList;
import com.github.tommyettinger.ds.LongList;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class JdkgdxdsTest {
    @Test
    public void testIntList() {
        Kryo kryo = new Kryo();
        kryo.register(IntList.class, new IntListSerializer());

        IntList data = IntList.with(-123, 0, 456, 0, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntList data2 = kryo.readObject(input, IntList.class);
            Assert.assertEquals(data, data2);
        }
    }
    @Test
    public void testLongList() {
        Kryo kryo = new Kryo();
        kryo.register(LongList.class, new LongListSerializer());

        LongList data = LongList.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongList data2 = kryo.readObject(input, LongList.class);
            Assert.assertEquals(data, data2);
        }
    }
}
