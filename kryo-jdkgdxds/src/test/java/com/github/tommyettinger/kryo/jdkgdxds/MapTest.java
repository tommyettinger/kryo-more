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
import com.github.tommyettinger.ds.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class MapTest {
    @Test
    public void testLongFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongFloatMap.class, new LongFloatMapSerializer());

        LongFloatMap data = LongFloatMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongFloatMap data2 = kryo.readObject(input, LongFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }
}
