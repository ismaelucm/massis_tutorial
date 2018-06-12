from subprocess import call
from sys import stdin

def configureProperties():
	print("Server ip:")
	ip = str(stdin.readline().strip())
	print("Server port:")
	port = str(stdin.readline().strip())
	print("Massis3-asset folder path:")
	path = str(stdin.readline().strip())
	propertiesPath = "./massis3-examples-server/profiles/dev/config.properties"
	file  = open(propertiesPath, 'r')
	propertyFile = file.read()
	lines = propertyFile.split("\n")
	fileOutput = ""
	for line in lines:
		if line == None or line == "":
			continue
		attrValue = line.split("=")
		attrName = str(attrValue[0]).strip()
		attrValue = str(attrValue[1]).strip()
		ok = True
		if attrName == 'host_ip':
			print ("host_port : old "+attrValue + " new "+ ip)
			if not ip == None and not ip == "":
				attrValue =  ip
		elif attrName == 'host_port':
			print ("host_path : old "+attrValue + " new "+ port)
			if not port == None and not port == "":
				attrValue =  port
		elif attrName == 'host_path':
			print ("host_path : old "+attrValue + " new "+ path)
			if not path == None and not path == "":
				attrValue =  path
		else:
			print("Know option "+attrName)
			ok = False

		if ok:
			fileOutput += attrName + "=" + attrValue +"\n"


	print("file output:")
	print(fileOutput)
	file.close()
	file  = open(propertiesPath, 'w')
	file.write(fileOutput)


print("Configure server properties? (y,N):")
configuring = stdin.readline()
configuring = configuring.strip()
print(configuring)
if configuring.upper() == "Y":
	print("Configuring..........")
	configureProperties()

call(["mvn", "compile"])
call(["mvn", "install"])