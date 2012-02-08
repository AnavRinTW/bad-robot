package com.systex.sop.cvs.command;

import java.io.File;

import com.badrobot.ui.containers.BadTrafficLabel;
import com.systex.sop.cvs.constant.CVSConst.CMD_RESULT;
import com.systex.sop.cvs.helper.CVSLog;
import com.systex.sop.cvs.util.PropReader;
import com.systex.sop.cvs.util.StringUtil;

public class ChkLogPathCommand extends StatusCommand {

	protected ChkLogPathCommand(BadTrafficLabel c) {
		super(c);
	}

	@Override
	public CMD_RESULT execute() {
		String path = PropReader.getProperty("CVS.LOG_PATH");
		if (StringUtil.isEmpty(path)) {
			CVSLog.getLogger().error("CVS.LOG_PATH must be set");
			return CMD_RESULT.FAILURE;
		}

		File f = new File(path);
		if (f.isDirectory()) {
			return CMD_RESULT.SUCCESS;
		} else {
			if (f.mkdirs())
				return CMD_RESULT.WARNING;
			else
				return CMD_RESULT.SUCCESS;
		}
	}

}
