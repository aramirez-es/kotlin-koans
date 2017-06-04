package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
    return this.addTimeIntervals(repeatedTimeInterval.interval, repeatedTimeInterval.repetitions)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(i: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, i)

data class RepeatedTimeInterval(val interval: TimeInterval, val repetitions: Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(value: MyDate): Boolean {
        return start <= value && value <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return DateIterator(this)
    }
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
