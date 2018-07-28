local current = redis.call('GET', KEYS[1])
if current == ARGV[1]
then
    return true
end
return false
