# huffman-code
An implementation of the Huffman Code in Java.

# The program's logic.

Logic of the program is as follows:

1. It will look for an input file named "Input.txt" in the resources folder. If it does not exist, it will create one for you.
    - The project already supplies a file by default. A mere test file.
    - The project is bundled with several example files that might be of interest to you. Check [NOTES.md](src/main/resources/Example%20files/NOTES.md) for more details.
    - Obviously, if you wish to see how the program compresses an specific text, you'll have to provide it yourself. All you would need to do in such case is to rename your text file to "Input.txt" and replace it for [the already existing file with such name](src/main/resources/Input.txt) in the resources folder.
2. It will show you the file's content on the console. Although long texts are useful for running compression benchmarks, they won't be shown quite well on console, and they will make it considerably harder to inspect how the program generated the routes for each one of its characters, so keep that in mind.
3. It will map each of the characters contained in the original text along with their corresponding frequencies and routes.
4. It will generate an appended route (a string), which contains each of the characters' generated routes in the order in which they appeared in the original text.
5. It will take the appended route, and it will split it into a list of strings of 8 characters long each.
6. It will convert each one of these strings into bytes, and it will write it into a binary file called "Output.bin"
7. It will read each one of these bytes from the binary file and convert it back into a list of strings of 8 characters each.
8. It will join the list of strings into a single string, the appended route.
9. Using this appended route, and the huffman tree itself, it will then decode the original text.
10. It will write this reconstructed text into an output file called "Output.txt". This file will be identical to the original file both in size and in its content.

# Possible limitations.

The program has a pitfall. When it came to the way the program encoded and decodes the data, I chose to work with strings instead of bit-shifting operations, frankly because it was just easier to do so, since converting a bit-string into a byte and then back is quite straightforward.
That came with the downside of possibly exceeding the maximum length that a string can have in Java, which is `Integer.MAX_VALUE`. For really, REALLY long files that threshold can be broken quite easily, resulting in an integer overflow. I might inspect into more clever ways to bypass this limitation in the future.
