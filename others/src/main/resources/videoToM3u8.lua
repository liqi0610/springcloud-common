--
-- Created by IntelliJ IDEA.
-- User: ZYW
-- Date: 2020-04-22
-- Time: 15:23
-- To change this template use File | Settings | File Templates.
--
local videoToM3u8 =  {}

function videoToM3u8.commonds(cmds)
    local result = os.execute(cmds.cmd1);
    if(result and (cmds.cmd2 ~= nil))then
        result = os.execute(cmds.cmd2);
    end
    return result;
end
return videoToM3u8

