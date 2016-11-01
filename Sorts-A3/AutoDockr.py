# This script automatically generates JavaDoc for the entire project.

import os
import re

pattern = re.compile('(public|protected|private|static|\s) +[\w\<\>\[\]]+\s+(\w+) *\([^\)]*\) *(\{?|[^;])')

for file in os.listdir("."):
    if file.endswith(".java"):
		with open(file) as f:
			content = f.readlines()
			for i in range(0, len(content)):
				string = content[i]
				se = re.search("(static)?(public|protected|private|\s)(static)? +([\w\<\>\[\]]+)\s+(\w+) *\(([^\)]*)\) *(\{?|[^;])", string)
				if se:
					string2 = " /**\n * " + str(se.group(5))
					if (se.group(6) != "" and se.group(6) != None):
						for param in str(se.group(6)).split(", "):
							string2 = string2 + "\n * @param " + param

					string2 = string2 + "\n * @return " + str(se.group(4)) + "\n */"
					string =  string2 + "\n" + string
				se2 = re.search("(public|private|protected|default)?\s*((abstract|static)\s*)?(class|enum)\s*([A-z]+)\s*(((implements|extends)\s*([A-z]+))?\s*)\{", string)
				if se2:
					string = " /**\n * " + str(se2.group(5)) + " (" + se2.group(4) + ")\n */\n" + string
				content[i] = string
				for item in content:
					print(item)
				with open(file, 'w') as f2: pass
				with open(file, 'w') as f3:
					for item in content:
						f3.write("%s" % item)
