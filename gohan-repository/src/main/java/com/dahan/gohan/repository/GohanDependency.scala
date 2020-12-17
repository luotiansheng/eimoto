package com.dahan.gohan.repository

/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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
 * Creates on 2020/12/9.
 */

import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.graph.Dependency

/**
 * 依赖基础信息类
 *
 * @author tiansheng
 */
class GohanDependency {

  private var groupId: String = _

  private var artifactId: String = _

  private var version: String = _

  private var scope: String = "compile"

  private var classifier: String = _

  /**
   * eclipse dependency object.
   */
  private var dependency: Dependency = _

  def this(coords: String, classifier: String, scope: String) {
    this()
    val gav: Array[String] = coords.split(":")
    pseudo(gav(0), gav(1), gav(2), classifier, scope)
  }

  def this(artifact: Artifact, scope: String) {
    this()
    pseudo(artifact.groupId, artifact.artifactId, artifact.version, artifact.classifier, scope)
  }

  /**
   * 伪构造器
   *
   * @param version    版本号
   * @param classifier 分类
   * @param scope      范围
   */
  private def pseudo(groupId: String, artifactId: String, version: String, classifier: String = null, scope: String = null): Unit = {
    this.groupId = groupId
    this.artifactId = artifactId
    this.version = version

    if (scope != null) {
      this.scope = scope
    }

    if (this.classifier == null) {
      this.dependency = new Dependency(new DefaultArtifact(s"$groupId:$artifactId:$version"), this.scope)
    } else {
      this.classifier = classifier
      this.dependency = new Dependency(new DefaultArtifact(groupId, artifactId, classifier, "jar", version), this.scope)
    }
  }

  def getGroupId: String = groupId

  def getArtifactId: String = artifactId

  def getVersion: String = version

  def getDependency: Dependency = dependency

}

object GohanDependency {

  def apply(coords: String): GohanDependency = new GohanDependency(coords, null, null)

  def apply(coords: String, classifier: String): GohanDependency = new GohanDependency(coords, classifier, null)

  def apply(coords: String, classifier: String, scope: String): GohanDependency = new GohanDependency(coords, classifier, scope)

  def apply(artifact: Artifact, scope: String): GohanDependency = new GohanDependency(artifact, scope)

}