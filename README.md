# Hashtables

I.Which is a better hash function:
x % n where n is a large number with several factors
x % n where n is a large prime number Prove your answer with a simple example.

I think when n is a prime number might be better becuase it is more unique(?).

II. Is char values summed % 599 a good hash function for strings? Why or why not?

Yes, becuase it is prime. The only issue could be that it is too big.

III.Take a look at Java's HashMap class and how it produces hashed values. 
(You may have to look at other classes to find the actual mathematical function! Follow the trail of breadcrumbs...)

It stores values in the map and then retrives the value when its key is called
