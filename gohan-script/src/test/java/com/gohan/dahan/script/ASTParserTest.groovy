package com.gohan.dahan.script

import com.dahan.gohan.Files
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.junit.Test

/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2020/12/16.
 */

/**
 * 测试抽象语法树解析
 * @author kevin
 */
class ASTParserTest
{

    String path = "/Users/wuyanzu/project/IdeaProjects/gohan/gohan-script/src/test/java/com/gohan/dahan/script/build.gohan"

    @SuppressWarnings('GroovyAssignabilityCheck')
    @Test
    void ast()
    {

        String scriptcode = Files.readString(path)

        def builder = new AstBuilder()
        def nodes = builder.buildFromString(scriptcode)

        def statement = nodes[0] as BlockStatement
        statement.statements.each { ExpressionStatement es ->

            println(es.text)

        }

    }

}