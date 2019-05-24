package info.goodline.reshenie_plus.extensions

import info.goodline.reshenie_plus.domain.model.Task
import info.goodline.reshenie_plus.domain.model.TaskRealm
import io.realm.RealmList

fun TaskRealm.map2Data() = Task (this.id, this.number, this.count)

fun Task.map2Realm(): TaskRealm {
    val taskRealm = TaskRealm()
    taskRealm.id = this.id
    taskRealm.number = this.number
    taskRealm.count = this.count
    return taskRealm
}

fun MutableList<Task>.map2RealmList(): RealmList<TaskRealm> {
    val rl = RealmList<TaskRealm>()
    this.forEach { rl.add(it.map2Realm()) }
    return rl
}

fun MutableList<TaskRealm>.map2DataList(): MutableList<Task> {
    val l = ArrayList<Task>()
    this.forEach { l.add(it.map2Data()) }
    return l
}