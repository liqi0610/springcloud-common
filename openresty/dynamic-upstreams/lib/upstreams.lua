-- local http = require "socket.http"
-- local ltn12 = require "ltn12"
local cjson = require "cjson"

local _M = {
    _VERSION="1.0"
}

function _M:update_upstreams()
    -- 从redis中拉去
    -- 动态更新upstream
    -- local resp = {}

    -- http.request{
    --     url = "http://127.0.0.1:9992/json", sink = ltn12.sink.table(resp)
    -- }

    local resp = cjson.decode('[{"Address":"127.0.0.1","ServicePort":9991},{"Address":"127.0.0.1","ServicePort":9992}]')

    local upstreams = {}
    for i, v in ipairs(resp) do
        upstreams[i] = {ip=v.Address, port=v.ServicePort}
    end

    ngx.shared.upstream_list:set("backends", cjson.encode(upstreams))
end

function _M:get_upstreams()
   local upstreams_str = ngx.shared.upstream_list:get("backends")
   return cjson.decode(upstreams_str)
end

return _M