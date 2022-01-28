import groovy.time.TimeCategory

println new Date().format( "yyyy-MM-dd'T'HH:mm:ss'Z'" )

def start = Date.parse( "yyyy-MM-dd'T'HH:mm:ss'Z'", "2021-11-09T09:51:51Z"  )
println start

def end = Date.parse( "yyyy-MM-dd'T'HH:mm:ss'Z'", "2021-12-07T09:51:51Z" )
println end

def period = TimeCategory.minus( end, start )

println period.seconds
println period.minutes
println period.hours
println period.days
println period.ago