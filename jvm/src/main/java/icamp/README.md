## 1A: Array Traversal 1 (Reverse)

`Given an array of numbers, replace each even number with two of the same number. e.g, [1,2,5,6,8, , , ,] -> [1,2,2,5,6,6,8,8].
Assume that the array has the exact amount of space to accommodate the result.`

### Ex & clarify
- Reuse the input array without changing size?
- 


### Solutions
- Traverse forward
  - If even:  slice array @ pos+1, add extra at pos+1, concat arrays
- Traverse forward, 2 arrays, discard input.  Different size, NO
- Traverse backward, 2 cursors
  - Builder cursor points at last empty space
  - Finder cursor scans until value, adds to builder.  If even add * 2 values

### Test cases
- Example input, inspect result for expected
- All inputs, check size is same as input
- Bad inputs
- All odd, all even

### Code
#### OO
Finder:
 - pos - init based on size
 - scan() - get next value.  ret pos?

Builder:
 - pos - init based on size
 - build(new_val)

Start with Traverser?
 - pos
 - direction (enum)
 - next(exp test)
 - write(pos, val)

### Verify

