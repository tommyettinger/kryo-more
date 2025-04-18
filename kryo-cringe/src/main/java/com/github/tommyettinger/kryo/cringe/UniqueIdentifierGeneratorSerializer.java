/*
 * Copyright (c) 2020-2024 See AUTHORS file.
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
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.UniqueIdentifier;

/**
 * Doesn't need anything else registered. The name doesn't match exactly; the class
 * this serializes and deserializes is {@link UniqueIdentifier.Generator}.
 * You usually will want to assign the result of {@link #read(Kryo, Input, Class)} to
 * {@link UniqueIdentifier#GENERATOR}. Unlike in earlier versions, this does not assign
 * to GENERATOR automatically.
 */
public class UniqueIdentifierGeneratorSerializer extends Serializer<UniqueIdentifier.Generator> {
    public UniqueIdentifierGeneratorSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final UniqueIdentifier.Generator data) {
        output.writeInt(data.getA());
        output.writeInt(data.getB());
        output.writeInt(data.getC());
        output.writeInt(data.getD());
    }

    @Override
    public UniqueIdentifier.Generator read(final Kryo kryo, final Input input, final Class<? extends UniqueIdentifier.Generator> dataClass) {
        return new UniqueIdentifier.Generator(input.readInt(), input.readInt(), input.readInt(), input.readInt());
    }

    @Override
    public UniqueIdentifier.Generator copy(Kryo kryo, UniqueIdentifier.Generator original) {
        return new UniqueIdentifier.Generator(original.getA(), original.getB(), original.getC(), original.getD());
    }
}
