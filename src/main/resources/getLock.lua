local lockKey = KEYS[1]
local lockValue = ARGV[1]
local timeout = ARGV[2]

if redis.call('setnx', lockKey, lockValue) == 1
then
    redis.call('expire', lockKey, timeout)
    return 1
else
    return 0
end
