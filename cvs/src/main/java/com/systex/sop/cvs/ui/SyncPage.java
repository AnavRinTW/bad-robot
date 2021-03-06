package com.systex.sop.cvs.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.systex.sop.cvs.schedular.CVSJob;
import com.systex.sop.cvs.ui.customize.comp.SSSJButton;
import com.systex.sop.cvs.ui.customize.comp.SSSJLabel;
import com.systex.sop.cvs.ui.customize.comp.SSSJSplitPane;
import com.systex.sop.cvs.ui.customize.comp.SSSJTabbedPane;
import com.systex.sop.cvs.ui.customize.comp.SSSJTable;
import com.systex.sop.cvs.ui.customize.comp.SSSJTextField;
import com.systex.sop.cvs.ui.customize.other.ObservingTextField;
import com.systex.sop.cvs.ui.customize.other.QueryActionListener;
import com.systex.sop.cvs.ui.customize.other.SSSDatePicker;
import com.systex.sop.cvs.ui.logic.SyncPageLogic;
import com.systex.sop.cvs.ui.tableClass.LogResultDO;
import com.systex.sop.cvs.util.PropReader;
import com.systex.sop.cvs.util.TimestampHelper;

/**
 * 同步功能頁面
 * <P>
 *
 */
@SuppressWarnings("serial")
public class SyncPage extends JPanel {
	// 控制變數
	
	// 幫助項目
	private SyncPageLogic logic = new SyncPageLogic();
	
	// 元件項目
	private SSSJTextField autoCron_jTxtF;
	private SSSJTextField autoDate_jTxtF;
	private SSSJButton autoExec_jBtn;
	private ObservingTextField manualDate_jTxtF;
	private SSSJTable table;
	private JCheckBox fullSync_jChkB;
	private SSSJButton manualExec_jBtn;
	private JCheckBox syncLog_jChkB;
	private JCheckBox syncWrite_jChkB;
	
	/** Constructor */
	public SyncPage() {
		setBorder(null);
		initial();
		initUI();
	}
	
	/** 切換「完整同步」按鈕 **/
	private void doSwitchFullSync() {
		if (getFullSync_jChkB().isSelected()) {
			getManualDate_jTxtF().setText("2000/01/01");
		} else {
			getManualDate_jTxtF().setText(TimestampHelper.convertToyyyyMMdd2(CVSJob.getAutoSyncDate()));
		}
	}

	private void initUI() {
		/** 初始化「自動同步」 **/
		getCron_jTxtF().setText(PropReader.getProperty("CVS.CRONTAB"));
		getAutoDate_jTxtF().setText(TimestampHelper.convertToyyyyMMdd2(CVSJob.getAutoSyncDate()));
		
		/** 初始化「手動同步」 **/
		getManualDate_jTxtF().setText(TimestampHelper.convertToyyyyMMdd2(CVSJob.getAutoSyncDate()));
	}
	
	private void initial() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);

		SSSJSplitPane sync_jSplit = new SSSJSplitPane();
		sync_jSplit.setBorder(null);
		sync_jSplit.setBackground(new Color(127, 125, 123));
		sync_jSplit.setDividerLocation(105);
		sync_jSplit.setOneTouchExpandable(false);
		sync_jSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		scrollPane.setViewportView(sync_jSplit);
		
		SSSJTabbedPane sync_jTab = new SSSJTabbedPane();
		sync_jSplit.setLeftComponent(sync_jTab);

		JPanel autoSyncTab = new JPanel();
		autoSyncTab.setBackground(Color.WHITE);
		sync_jTab.addTab("自動同步", null, autoSyncTab, null);
		autoSyncTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("25dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("30dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("18dlu"),
				RowSpec.decode("18dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		SSSJLabel label = new SSSJLabel("日期");
		autoSyncTab.add(label, "2, 2, right, default");

		autoDate_jTxtF = new ObservingTextField();
		autoDate_jTxtF.setEditable(false);
		autoSyncTab.add(autoDate_jTxtF, "4, 2, fill, default");
		autoDate_jTxtF.setColumns(10);

		SSSJLabel label_1 = new SSSJLabel("排程");
		autoSyncTab.add(label_1, "2, 3, right, default");

		autoCron_jTxtF = new SSSJTextField();
		autoCron_jTxtF.setEditable(false);
		autoSyncTab.add(autoCron_jTxtF, "4, 3, fill, default");
		autoCron_jTxtF.setColumns(10);

		autoExec_jBtn = new SSSJButton(SSSJButton.ITEM_LITE);
		autoExec_jBtn.setBackground(Color.WHITE);
		
		// XXX 「自動同步」(啟動 / 停止)
		autoExec_jBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logic.doAutoSyncExecute(autoExec_jBtn);
			}
		});
		autoExec_jBtn.setText("啟動");
		autoSyncTab.add(autoExec_jBtn, "8, 3");

		JPanel manualSyncTab = new JPanel();
		manualSyncTab.setBackground(Color.WHITE);
		sync_jTab.addTab("手動同步", null, manualSyncTab, null);
		manualSyncTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("25dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:30dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("30dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("74dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("18dlu"),
				RowSpec.decode("18dlu"),}));

		SSSJLabel label_2 = new SSSJLabel("日期");
		manualSyncTab.add(label_2, "2, 2, right, default");

		manualDate_jTxtF = new ObservingTextField();
		manualDate_jTxtF.setEditable(false);
		manualSyncTab.add(manualDate_jTxtF, "4, 2, fill, default");
		manualDate_jTxtF.setColumns(10);

		JButton datePicker_jBtn = new JButton();
		datePicker_jBtn.setBorder(null);
		datePicker_jBtn.setBackground(Color.WHITE);
		datePicker_jBtn.setIcon(new ImageIcon(StartUI.class.getResource("/resource/search-calendar.png")));
		datePicker_jBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SSSDatePicker dp = new SSSDatePicker(manualDate_jTxtF, Locale.TAIWAN);
				Date selectedDate = dp.parseDate(manualDate_jTxtF.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(manualDate_jTxtF);
			}
		});
		manualSyncTab.add(datePicker_jBtn, "6, 2");
		
		SSSJButton button = new SSSJButton();
		button.setBackground(Color.WHITE);
		
		// XXX 中斷同步
		button.addActionListener(new QueryActionListener(button) {
			public void actPerformed(ActionEvent e) {
				logic.doInterrupt();
			}
		});
		button.setText("中斷");
		manualSyncTab.add(button, "8, 2");
		
		syncLog_jChkB = new JCheckBox("同步LOG");
		syncLog_jChkB.setSelected(true);
		syncLog_jChkB.setBackground(Color.WHITE);
		manualSyncTab.add(syncLog_jChkB, "12, 2");

		SSSJLabel label_3 = new SSSJLabel();
		label_3.setText("範圍");
		manualSyncTab.add(label_3, "2, 3, right, default");

		fullSync_jChkB = new JCheckBox("完全同步");
		fullSync_jChkB.setBackground(Color.PINK);
		
		// XXX 切換「完整同步」按鈕
		fullSync_jChkB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSwitchFullSync();
			}
		});
		manualSyncTab.add(fullSync_jChkB, "4, 3");

		manualExec_jBtn = new SSSJButton(SSSJButton.ITEM_LITE);
		manualExec_jBtn.setBackground(Color.WHITE);
		
		// XXX 執行「手動同步」
		manualExec_jBtn.addActionListener(new QueryActionListener(manualExec_jBtn) {
			public void actPerformed(ActionEvent e) {
				if (!getSyncLog_jChkB().isSelected() && !getSyncWrite_jChkB().isSelected()) {
					JOptionPane.showMessageDialog(SyncPage.this, "「同步LOG」與「 同步WRITE」至少選一項");
				}else{
					logic.doManualSyncExecute(
							getSyncLog_jChkB().isSelected(),
							getSyncWrite_jChkB().isSelected(),
							getManualDate_jTxtF().getText(),
							getFullSync_jChkB().isSelected() );
				}
			}
		});
		manualExec_jBtn.setText("執行");
		manualSyncTab.add(manualExec_jBtn, "8, 3");
		
		syncWrite_jChkB = new JCheckBox("同步WRITE");
		syncWrite_jChkB.setSelected(true);
		syncWrite_jChkB.setBackground(Color.WHITE);
		manualSyncTab.add(syncWrite_jChkB, "12, 3");
		
				JScrollPane syncResult_jScrP = new JScrollPane();
				sync_jSplit.setRightComponent(syncResult_jScrP);
				syncResult_jScrP.setBackground(Color.ORANGE);

		table = new SSSJTable(new LogResultDO());
		table.setPreferredScrollableViewportSize(new Dimension(450, 200));
		syncResult_jScrP.setViewportView(table);
	}

	public SSSJTextField getAutoDate_jTxtF() {
		return autoDate_jTxtF;
	}

	public SSSJButton getAutoExec_jBtn() {
		return autoExec_jBtn;
	}

	public SSSJTextField getCron_jTxtF() {
		return autoCron_jTxtF;
	}

	public JCheckBox getFullSync_jChkB() {
		return fullSync_jChkB;
	}

	public ObservingTextField getManualDate_jTxtF() {
		return manualDate_jTxtF;
	}

	public SSSJButton getManualExec_jBtn() {
		return manualExec_jBtn;
	}

	public JTable getTable() {
		return table;
	}

	public JCheckBox getSyncLog_jChkB() {
		return syncLog_jChkB;
	}

	public JCheckBox getSyncWrite_jChkB() {
		return syncWrite_jChkB;
	}
}
