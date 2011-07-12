
def fib(term) {
  if (term < 3) {
    return 1;
  }
  return fib(term-2) + fib(term-1)
}

def fibLoop(term) {
  if (term < 3) return 1

  def first = 1
  def second = 1;
  def last=0

  3.upto term, {
    last = first + second
    first = second
    second = last
  }
  return last
}

println "" + fib(1) + " " +fibLoop(1)
println "" + fib(2) + " " +fibLoop(2)
println "" + fib(3) + " " +fibLoop(3)
println "" + fib(4) + " " +fibLoop(4)
println "" + fib(5) + " " +fibLoop(5)
println "" + fib(6) + " " +fibLoop(6)
println "" + fib(15) + " " + fibLoop(15)
