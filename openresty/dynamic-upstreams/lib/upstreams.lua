local http = require "socket.http"
local ltn12 = require "ltn12"
local cjson = require "cjson"

local _M = {
    _VERSION="1.0"
}

function _M:update_upstreams()
    -- 从redis中拉去
    -- 动态更新upstream
    local resp = {}

   local res, code, response_headers =  http.request{
        url = "http://192.168.1.3:9992/json", sink = ltn12.sink.table(resp)
    }
    ngx.log(ngx.INFO,"00000000000000: ",res)
    ngx.log(ngx.INFO,"11111111111111111",code)
    ngx.log(ngx.INFO,"22222222222222222222222222222: ",cjson.encode(resp))

    local resp = cjson.decode(resp[1])

    local upstreams = {}
    for i, v in ipairs(resp) do
        upstreams[i] = {ip=v.Address, port=v.ServicePort}
    end
    ngx.log(ngx.INFO,"444444444444444444444444444",cjson.encode(upstreams));
    ngx.shared.upstream_list:set("backends", cjson.encode(upstreams))
end

function _M:get_upstreams()
   local upstreams_str = ngx.shared.upstream_list:get("backends")
   ngx.log(ngx.INFO,"33333333333333333333333333333333",upstreams_str)
   return cjson.decode(upstreams_str)
end

return _M
