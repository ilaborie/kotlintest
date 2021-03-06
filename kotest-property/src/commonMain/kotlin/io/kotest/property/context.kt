package io.kotest.property

/**
 * A [PropertyContext] is used when executing a propery test.
 * It allows feedback and tracking of the state of the property test.
 */
class PropertyContext {

   private var successes = 0
   private var failures = 0
   private val classifications = mutableMapOf<String, Int>()

   fun markSuccess() {
      successes++
   }

   fun markFailure() {
      failures++
   }

   fun successes() = successes
   fun failures() = failures

   fun attempts(): Int = successes + failures

   fun classifications(): Map<String, Int> = classifications.toMap()

   fun classify(condition: Boolean, trueLabel: String) {
      if (condition) {
         val current = classifications.getOrElse(trueLabel) { 0 }
         classifications[trueLabel] = current + 1
      }
   }

   fun classify(condition: Boolean, trueLabel: String, falseLabel: String) {
      if (condition) {
         classify(condition, trueLabel)
      } else {
         classify(!condition, falseLabel)
      }
   }
}
