# This script automatically generates JavaDoc for the entire project.

import os
import re
import sys

pattern = re.compile('(public|protected|private|static|\s) +[\w\<\>\[\]]+\s+(\w+) *\([^\)]*\) *(\{?|[^;])')

if(len(sys.argv) > 1):
	with open(sys.argv[1]) as f:
		content = f.readlines()
		for i in range(0, len(content)):
			string = content[i]
			se = re.search("(static)?(public|protected|private|\s)(static)? +([\w\<\>\[\]]+)\s+(\w+) *\(([^\)]*)\) *(\{?|[^;])", string)
			if se:
				paramStr = ""
				if (se.group(6) != "" and se.group(6) != None):
					for idx, param in enumerate(str(se.group(6)).split(", ")):
						if idx == (len(str(se.group(6)).split(", "))-1):
							paramStr = param + paramStr
						else:
							paramStr = ", " + param + "" + paramStr
				description = raw_input("Description for " + str(se.group(4)) + " " + str(se.group(5)) + "(" + paramStr +  "): ")
				string2 = " /**\n * " + str(se.group(5)) + " \n * " + description
				if description == "":
					string2 = "!nope!"
				if (se.group(6) != "" and se.group(6) != None):
					for param in str(se.group(6)).split(", "):
						string2 = string2 + "\n * @param " + param
				string2 = string2 + "\n * @return " + str(se.group(4)) + "\n */"
				string =  string2 + "\n" + string
			se2 = re.search("(public|private|protected|default)?\s*((abstract|static)\s*)?(class|enum)\s*([A-z]+)\s*(((implements|extends)\s*([A-z]+))?\s*)\s*(<[A-Za-z,\s]*>)?\s*\{", string)
			if se2:
				description = raw_input("Description for " + str(se2.group(4) + " " + str(se2.group(5)) + ": "))
				string3 = ""
				if description == "":
					string3 = "!nope!"
				string = string3 + " /**\n * " + str(se2.group(5)) + " (" + se2.group(4) + ")\n " + " * " + description + "\n" + " */\n" + string
			if not "!nope!" in string:
				content[i] = string
		with open(sys.argv[1], 'w') as f2: pass
		with open(sys.argv[1], 'w') as f3:
			for item in content:
				f3.write(("%s" % item.rstrip()) + '\n')
	os.system("uncrustify -c ~/.uncrustify_configs/default.cfg --replace " + str(sys.argv[1]))
	os.system("rm -rf " + str(sys.argv[1]) + ".unc-backup.md5~")
	os.system("rm -rf " + str(sys.argv[1]) + ".unc-backup~")
else: print("Please supply a file argument")
