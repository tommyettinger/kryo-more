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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.digital.Base;
import com.github.tommyettinger.random.DistributionWrapper;

/**
 * Kryo {@link Serializer} for juniper {@link DistributionWrapper}s.
 * Internally, this stores the serialized String produced by {@link DistributionWrapper#stringSerialize(Base)}.
 * This allows it to reliably store the Distribution and ReductionMode it needs.
 */
public class DistributionWrapperSerializer extends Serializer<DistributionWrapper> {

    public DistributionWrapperSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final DistributionWrapper data) {
        output.writeString(data.stringSerialize(Base.BASE86));
    }

    @Override
    public DistributionWrapper read(final Kryo kryo, final Input input, final Class<? extends DistributionWrapper> dataClass) {
        DistributionWrapper random = new DistributionWrapper(0L);
        random.stringDeserialize(input.readString(), Base.BASE86);
        return random;
    }

    @Override
    public DistributionWrapper copy(Kryo kryo, DistributionWrapper original) {
        return original.copy();
    }
}