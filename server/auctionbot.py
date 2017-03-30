import urllib.request
import json
import time
from firebase import firebase

times = [];

url = 'https://wow-deal-finder.firebaseio.com/'

firebase = firebase.FirebaseApplication(url, None)

def uploadToFirebase(contents):
  itemPrices = {}
  parsed = json.loads(contents)

  for auction in parsed['auctions']:
    itemid = int(auction['item'])
    buyout = int(auction['buyout'])

    if itemid in itemPrices:
      itemPrices[itemid] = min(buyout, int(itemPrices[itemid]))
    else:
      itemPrices[itemid]= buyout

  result = firebase.put('/auctions', itemid , itemPrices)

  print(itemPrices)

def initdb(jsonPath):
  itemsleft = 100000 #carefully calculate with empirical research
  with open(jsonPath) as data:
    jdata = json.load(data)

    for lawl in jdata:
      itemid = lawl['data']['id']
      level = lawl['data']['itemLevel']
      name_enus = lawl['data']['name']
      price = getPrice(itemid)
      quality = lawl['data']['quality']
      requiredLevel = lawl['data']['requiredLevel']

      daturs = {'level': level, 'name_enus': name_enus, 'price': price, 'quality': quality, 'requiredLevel': requiredLevel}
      result = firebase.put('/items', itemid , daturs)
      itemsleft = itemsleft - 1
      if itemsleft % 10 == 0:
        print(itemsleft)

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
      uploadToFirebase(contents)
    else:
      print("already seen")

  print(realmSlug + ' saved')


while True:
  GetData('magtheridon', 'Magtheridon')
  time.sleep(60*40)
