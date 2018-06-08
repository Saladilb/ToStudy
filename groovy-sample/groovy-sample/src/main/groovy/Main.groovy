def someVariable = 'Some value'
print("Hello world, ${someVariable}")

def funcWithoutArg() {
    println('without args')
}

funcWithoutArg()

def funcWithArgs(first, second) {
    println(first)
    println(second)
}

//funcWithArgs('f', 's')
funcWithArgs 'f', 's'

def funcWithArgsMap(map) {
    println map['first']
}

funcWithArgsMap first: 'value'

def closure = {
    println 'closure'
}
closure()

def closureWithArgs = { value ->
    println value
}
closureWithArgs 'sample value'

def alias = closure
alias()

def listData = ['f', 's', 't']
listData.each {
    println it
}

class Person {
    def id
    def name
}

def person = new Person()
person.name = 'Vasya'

def closureWithDelegate = {
    print name
}
closureWithDelegate.delegate = person
closureWithDelegate()