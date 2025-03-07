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

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.UniqueIdentifier;
import org.junit.Assert;
import org.junit.Test;

public class IdentifierTest {
    @Test
    public void testUniqueIdentifier() {
        Kryo kryo = new Kryo();
        UniqueIdentifierSerializer ser = new UniqueIdentifierSerializer();
        kryo.register(UniqueIdentifier.class, ser);

        UniqueIdentifier.GENERATOR.stringDeserialize("FFFFFFFF$FFFFFFFF$EEEEEEEE$EEEEEEEE");
        UniqueIdentifier data = UniqueIdentifier.next();

        Output output = new Output(32, -1);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            UniqueIdentifier data2 = kryo.readObject(input, UniqueIdentifier.class);
            Assert.assertEquals(data, data2);
        }
    }
    @Test
    public void testUniqueIdentifierGenerator() {
        Kryo kryo = new Kryo();
        UniqueIdentifierGeneratorSerializer ser = new UniqueIdentifierGeneratorSerializer();
        kryo.register(UniqueIdentifier.class, ser);
        kryo.register(UniqueIdentifier.Generator.class, ser);

        UniqueIdentifier.GENERATOR.stringDeserialize("FFFFFFFF$FFFFFFFF$EEEEEEEE$EEEEEEEE");
        UniqueIdentifier.Generator data = UniqueIdentifier.GENERATOR;

        Output output = new Output(32, -1);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();

        UniqueIdentifier tester = UniqueIdentifier.next();

        try (Input input = new Input(bytes)) {
            UniqueIdentifier.GENERATOR = kryo.readObject(input, UniqueIdentifier.Generator.class);
            Assert.assertEquals(tester, UniqueIdentifier.next());
        }
    }
}
