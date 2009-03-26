package helpers

import java.util.regex.Pattern

class URIHelper {

    public static String absoluteToRelative (String file, String parentDirectory) {
            Stack<String> stackFile = new Stack<String>();
            Stack<String> stackDir = new Stack<String>();

            file.split(Pattern.quote(File.separator)).reverse().each
            {
                stackFile.push(it)
            }
            parentDirectory.split(Pattern.quote(File.separator)).reverse().each
            {
                stackDir.push(it)
            }

            while ( !stackDir.empty() && !stackFile.empty() && stackFile.peek().equals(stackDir.peek())) {
                stackFile.pop();
                stackDir.pop();
            }

            String relativeFile = ""

            while (!stackDir.empty())
            {
                relativeFile +=  ".." + File.separator
                stackDir.pop();
            }

            while (stackFile.size() != 1)
            {
                relativeFile +=  stackFile.pop() + File.separator
            }

            relativeFile +=  stackFile.pop()

            return relativeFile;
        }



}