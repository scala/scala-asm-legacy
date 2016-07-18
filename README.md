# A fork of ASM for the Scala Compiler

[<img src="https://img.shields.io/travis/scala/scala-asm.svg"/>](https://travis-ci.org/scala/scala-asm)
[<img src="https://img.shields.io/maven-central/v/org.scala-lang.modules/scala-asm.svg"/>](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aorg.scala-lang.modules%20a%3Ascala-asm)

This repository contains a fork of the ASM Java bytecode manipulation and analysis framework for the Scala compiler.

The package name is changed to `scala.tools.asm`.
There is a small number of other patches applied to the original sources.
Note that this fork only contains a subset of the source files of the ASM repository.

Longer-term, we plan to retire this fork and move to stock ASM.
See [issue #4](https://github.com/scala/scala-asm/issues/4) for details.

## Keeping in Synch

When upgrading to a new ASM release, we always import the unmodified source files and re-apply all our patches on top.
This makes it easy to see how our fork differs from the official release.

## Current Version

The current sources are based on the following version of ASM ([browse tags here](http://websvn.ow2.org/listing.php?repname=asm&path=%2Ftags%2F)):

```
Version 5.1, SVN r1798, tags/ASM_5_1
```

Previous ASM Upgrade PR: https://github.com/scala/scala-asm/pull/5

## Upgrading ASM

Start by deleting all source files and copy the ones from the latest ASM release.

The original ASM sources are in an [SVN repository](http://forge.ow2.org/plugins/scmsvn/index.php?group_id=23), which is mirrored here: https://github.com/lrytz/asm.
You can use this mirror, your own git-svn mirror, or the original SVN repository to grab the sources of a new ASM version.
A description how to work with the git-svn clone is here: https://github.com/lrytz/asm/issues/1.

Excluded Files (don't copy):
  * `package.html` files
  * `org/objectweb/asm/commons`, but keep `CodeSizeEvaluator.java`
  * `org/objectweb/asm/optimizer`
  * `org/objectweb/asm/xml`

Take a look at the previous PR that upgraded ASM [(see above)](#current-version).
Follow the upgrade procedure in the same way.

The re-packaging and cleanup commits can be applied using the following commands:
  * convert line endings (there are some `CRLF`)  
    `find src -name '*.java' | xargs dos2unix`
  * change package clauses  
    `find src -name '*.java' | xargs sed -i '' -e 's/package org\.objectweb\.asm/package scala.tools.asm/'`
  * update imports  
    `find src -name '*.java' | xargs sed -i '' -e 's/import org\.objectweb\.asm/import scala.tools.asm/'`
  * update `@links`, `@associates`  
    `find src -name '*.java' | xargs sed -i '' -e 's/@link org\.objectweb\.asm/@link scala.tools.asm/'`  
    `find src -name '*.java' | xargs sed -i '' -e 's/@associates org\.objectweb\.asm/@associates scala.tools.asm/'`
  * remove trailing whitespace  
    `find src -name '*.java' | xargs sed -i '' -e 's/[ ]*$//'`

Cherry-pick the actual changes that we have in our fork:
  * Include the commits labelled `[asm-cherry-pick]` in the previous upgrade PR
  * Include the changes to `src` that were added since the last upgrade, and label them `[asm-cherry-pick]`

Update the ["Current Version"](#current-version) section of this README.
