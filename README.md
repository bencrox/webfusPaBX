PaBX  API
=========

##### Global settings

1. Account id  $id :  valid Email url, unicode, no longer than 255 char
2. Account password $pw : unicode, no longer than 64 char
3. Protocol version $protover :  1.0.0 
4. $baseurl:  (ver 1.0.0 , only in HTTP)    

	domain + 

	optional port (ver 1.0.0 , default 80)

	optional path (default /)

	http://pbx.webfus.com:3232/fotan

5. Client version hash $client : six hex digit version of the client software


Remarks, all JSON are supposed to be no longer than 1k byte if not marked "long JSON" / "huge JSON"

long JSON are supposed to be no longer then 100k byte 

huge JSON are supposed to be no longer then 2m byte

---

## Version check 

Confirm protocol compatibility, server version hash and uptime.

```
GET $baseurl/v/$protover
```

Return JSON, variant of 

```
{"status":"OK","server":"c3264a","since":1481852170000,.....}
```

Does not matter as long as "status" is "OK"

The 6 hex digit server version hash will be mark as $server in below

---

## ENUM config

A list of labelled items to convert binary config from protocol 

```
GET $baseurl/e/$server
```

Return huge JSON, variant of 

```
{"enum":$server,
 "perm":["READ_LOG",...."TOGGLE_ROUTE"....],
 ... }
```

This config file is supposed to be saved in client, and update only if requested by version check API advice to do "enum":"UPDATE"

---

## Login

Post a json of client credentials and information

```
POST $baseurl/login
```

with JSON, variant of 

```
{"id":$id,"pw":$pw,"client":$client,....}
```

Return JSON, variant of 

```
{"login":"OK","token":"UUID....","e-perm":$perm,....}
```

In case login is NOT "OK", there will be $error_code and $error_msg to be shown

$token is an UUID for any further access within this session.

Every "e-" suffixed item implies a binary little endian representation of enum LABELs toggled on.
For example:

```
"perm":["READ_LOG","PLAY_VOICE_LOG","TOGGLE_ROUTE","ADD_ROUTE"]
"e-perm":3
```

mean the user shall have "perm" [binary] 11 turned on, 
which mean he/she can "READ_LOG", "PLAY_VOICE_LOG", but no right to "ADD_ROUTE"

---

## System Status / Heart beat

latest system status summary, by what the user can access

```
GET $baseurl/!/$token
```

Return JSON, variant of 

```
{"status":{"line":2,"ringing":1,"talking":0,"voicemail":2,...},"update":{"logs":[],"count":21,"since":1481852170000},.....}
```

Notes: In any returned JSON within a session, there might be an "status" element which contain exactly what system status might reply at the same time. Yet status is only optional in JSON replies.

The "update" contain at most 10 readable new call log/update/alerts stacked up "since" the last heartbeat.

The number of logs is limited so that the heart heat will be limited to be 1k byte long at most. 

Logs within heart beat might be a precise version which is not a FULL log.

---

## Standard Call log query for synchronization and update

get 10 FULL logs and updates in long JSON which the user can access since a specific time $t ( in microseconds )

```
GET $baseurl/t/$t/$token
```

Return JSON, variant of 

```
{"logs":[....],...}
```

---
## Standard Call log query by date 

get 10 FULL logs (no updates will be attached) in long JSON


Method 1 - Simple get, no filter, date is formated in YYYY-MMM-DD (HKT) such as 2017-FEB-03, case insensitve. 

```
GET $baseurl/d/$YYYY-MMM-DD/$token
```

Return JSON, variant of 

```
{"logs":[....],...}
```

Method 2 - Post, with more options  (Not to be implemented at sprint 1)

```
POST $baseurl/d
```

with JSON, variant of 

```
{"token":$token,"date":$YYYY-MM-DD,"timezone":$tz,"any_match":$match_str,"caller_prefix":$caller_prefix,....}
```

$tz is any text base location which can be handled by moment.js, any space will be replaced by underscore :

http://momentjs.com/timezone/

e.g. "Asia/Hong_Kong"

$match_str is a simple text string, with at least 3 characters, which will be escaped and surrounded by wildcard SQL search. 

$xxx_prefix is a simple text string, with at least 1 character, which will be escaped and matched with specific field $xxx

$xxx_suffix is a simple text string, with at least 1 character, which will be escaped and matched with specific field $xxx

$xxx_gt is an integer of seconds, which will be matched with time interval fields 

$xxx is a simple text string, with at least 2 characters to be escaped, or boolean (true,false) for Exact match with a field.

$xxx_not is a simple text string, with at least 2 characters, which will be escaped and surrounded by wild card to exlucde from search.

$page is a positive integer, which will be multipled by 10, to skip records

Return JSON, variant of 

```
{"logs":[....],...,"count":$count}
```

$count is the total number of records that match the caterion.

---

## Standard Call log query by Caller / Extension / Agent   ... (not to be implemented in sprint 1) 

Get 10 FULL call log specific by a phone number, either caller/extension/agent

```
GET $baseurl/n/$number/$token
```

Return JSON, variant of 

```
{"logs":[....],...,"count":$count}
```

TBD

---

## Get call routes

get at most 200 call routes readable by user

Method 1 - Simple get 

```
GET $baseurl/r/$token(/$page)
```

$page is optional positive integer, a multiplier skip for 200 records 

Return JSON, variant of 

```
{"routes":[....],...,"count":$count}
```

---
## Add/Update call routes

Method 1 - simple add/update

```
POST $baseurl/callroute
```

with JSON, variant of 

```
{"token":$token,"routes":[...]}
```

For each route:

"id":$id | UUID | Required for updates | - 
"exten":$exten | text | Required for add new |  full HK eight digit number assigned to user, not unique
"dest":$dest | text | Required for add new | (version 1) full HK eight digit number for pickup. not unique
"caller":$caller | text | Optional | route to handle VIP caller, full HK eight digit number
"email":$email | email | Optional | email address to get missed call log and/or voicemail 
"desc":$desc | text | Optional | simple text description, with 200 characters for the route
"group":$group | text | Required for add new | 2-12 characters, case sensitive, for grouping toggle
"switch":$sw | text/boolean | Optional | text "on"/"off" or boolean. default "off"
"expire_at":$expire | timestamp | Optional | timestamp in unix epoch (seconds) to invalid the route
"imt":$imt | text | Optional |  type and token for install messaging.

---
## Delete


###



TBC....


