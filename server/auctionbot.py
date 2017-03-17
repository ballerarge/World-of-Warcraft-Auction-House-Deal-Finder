import urllib.request
import json
import time

times = [];


def GetData(realmSlug, RealmDirectory):
  response = urllib.request.urlopen('https://us.api.battle.net/wow/auction/data/' + realmSlug + '?locale=en_US&apikey=jzntya796yhp4w9w5nk736e6bv5szsd8')
  daturs = response.read().decode('utf8')
  parsed = json.loads(str(daturs))

  for url in parsed["files"]:
    lastmodified = url['lastModified']

    if lastmodified not in times:
      times.append(lastmodified)
      tmp = urllib.request.urlopen(url['url'])
      contents = tmp.read().decode('utf8')
      file = open(RealmDirectory + '/'+  str(lastmodified) + '.json', 'w');
      file.write(contents);
      file.close()
    else:
      print("already seen")

  print(realmSlug + ' saved')


while True:
  GetData('magtheridon', 'Magtheridon')
  GetData('dark-iron', 'Dark Iron')
  
  time.sleep(60*40)
