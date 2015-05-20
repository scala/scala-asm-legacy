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

The current sources are based on the following version of ASM ([browse tags here](http://websvn.ow2.org/listing.php?repname=asm&path=%2Ftags%2F&peg=1748)):

```
Version 5.0.3, SVN r1748, tags/ASM_5_0_3
```

## Upgrading ASM

Start by deleting all source files and copy the ones from the latest ASM release.

The original ASM sources are in an SVN repository, which is mirrored here: https://github.com/lrytz/asm.
You can use this mirror, your own git-svn mirror, or the original SVN repository to grab the sources of a new ASM version.
A description how to work with the git-svn clone is here: https://github.com/lrytz/asm/issues/1.

Excluded Files (don't copy):
  * `package.html` files
  * `org/objectweb/asm/commons`, but keep `CodeSizeEvaluator.java`
  * `org/objectweb/asm/optimizer`
  * `org/objectweb/asm/xml`

*The below will change once a first is done in the new `scala/scala-asm` repository.*
*In the new repository, it probably makes sense to only squash the "Re-packaging and cosmetic changes".*
*The "actual changes" can then stay in the commit history.*

Check the commit history of `src/asm`: https://github.com/scala/scala/commits/2.11.x/src/asm.
Find the previous commit that upgraded ASM and take a look at its commit message.
It should be a squashed version of a pull request that shows the precise procedure how the last upgrade was made.

Re-packaging and cosmetic changes:
  * convert line endings (there are some `CRLF`)  
    `find src/asm/scala/tools/asm -name '*.java' | xargs dos2unix`
  * change package clauses  
    `find src/asm/scala/tools/asm -name '*.java' | xargs sed -i '' -e 's/package org\.objectweb\.asm/package scala.tools.asm/'`
  * update imports  
    `find src/asm/scala/tools/asm -name '*.java' | xargs sed -i '' -e 's/import org\.objectweb\.asm/import scala.tools.asm/'`
  * update `@links`, `@associates`  
    `find src/asm/scala/tools/asm -name '*.java' | xargs sed -i '' -e 's/@link org\.objectweb\.asm/@link scala.tools.asm/'`  
    `find src/asm/scala/tools/asm -name '*.java' | xargs sed -i '' -e 's/@associates org\.objectweb\.asm/@associates scala.tools.asm/'`
  * remove trailing whitespace  
    `find src/asm/scala/tools/asm -name '*.java' | xargs sed -i '' -e 's/[ ]*$//'`

Include the actual changes that we have in our repostiory
  * Include the commits labelled `[asm-cherry-pick]` in the non-squashed PR of the previous upgrade
  * Include the changes that were added to src/asm since the last upgrade and label them `[asm-cherry-pick]`
