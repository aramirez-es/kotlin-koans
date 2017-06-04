package i_introduction._1_Java_To_Kotlin_Converter

import util.JavaCode

fun todoTask1(collection: Collection<Int>): Nothing = util.TODO(
        """
        Task 1.
        Rewrite JavaCode1.task1 in Kotlin.
        In IntelliJ IDEA, you can just copy-paste the code and agree to automatically convert it to Kotlin,
        but only for this task!
    """,
        references = { JavaCode1().task1(collection) })

class JavaCode1ToKotlin {
    fun task1(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("{")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("}")
        return sb.toString()
    }
}

fun task1(collection: Collection<Int>): String {
    return JavaCode1ToKotlin().task1(collection)
}
