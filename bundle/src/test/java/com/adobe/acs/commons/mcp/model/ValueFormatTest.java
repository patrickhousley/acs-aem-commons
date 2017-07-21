/*
 * Copyright 2017 Adobe.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.adobe.acs.commons.mcp.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic sanity check of value formats in MCP
 */
public class ValueFormatTest {
    enum fieldEnum {
        @FieldFormat(ValueFormat.plain)
        somePlainField,
        @FieldFormat(ValueFormat.storageSize)
        someStorageSizeField,
        someUnannotatedField
    }
    
    @Test
    public void testDefinedAnnotations() {
        assertEquals(ValueFormat.plain, ValueFormat.forField(fieldEnum.somePlainField));
        assertEquals(ValueFormat.storageSize, ValueFormat.forField(fieldEnum.someStorageSizeField));
    }
    
    @Test
    public void testUndefinedAnnotations() {
        assertEquals(ValueFormat.plain, ValueFormat.forField(fieldEnum.someUnannotatedField));
    }
    
    @Test
    public void oneKb() {
        assertEquals("1.0 kb", ValueFormat.getHumanSize(1 << 10));
    }
    
    @Test
    public void oneMb() {
        assertEquals("1.0 mb", ValueFormat.getHumanSize(1 << 20));
    }    
    
    @Test
    public void oneGb() {
        assertEquals("1.0 gb", ValueFormat.getHumanSize(1 << 30));
    }
    
    @Test
    public void onetb() {
        assertEquals("1.0 tb", ValueFormat.getHumanSize(((long) (1 << 30)) * 1024L));
    }
}
