@echo off

@rem ��Դ·��
set s=x:\resource\android��ֵ��ͼ��˵���ĵ�\android��ֵ��ͼ\

@rem Ŀ��·��
set p=%~dp0\..\assets\zz_res\drawable\

@rem ǰ׺
set pr=cc_

@rem ����
set t=
call :myRm ��ֵ���ģ�������ߣ�\recharge_ban.png recharge_ban.png
call :mycp ��ֵ���ģ�������ߣ�\recharge_ban_2.9.png recharge_ban.9.png
@rem call :mycp ��ֵ���ģ��������룩\��ֵ_09.png charge_pull.png

set t=
call :myRm �һ��б�\ex_button.png ex_button.png
@rem call :mycp �һ��б�\ex_button.9.png ex_button.9.png
call :mycp �һ��б�\ex_button_2.9.png ex_button.9.png
call :myRm �һ��б�\ex_button_click.png ex_button_click.png
@rem call :mycp �һ��б�\ex_button_click.9.png ex_button_click.9.png
call :mycp �һ��б�\ex_button_click_2.9.png ex_button_click.9.png
call :mycp �һ��б�\ex_Right.png ex_right.png
call :mycp �һ��б�\ex_Right_click.png ex_right_click.png

set t=
@rem call :mycp �һ�����\Background.png background.png
@rem call :mycp �һ�����\Background.9.png background.9.png
call :mycp �һ�����\Background_2.9.png background.9.png
call :myRm �һ�����\buy_button.png buy_button.png
call :myRm �һ�����\buy_button_click.png buy_button_click.png
call :mycp �һ�����\buy_button.9.png buy_button.9.png
call :mycp �һ�����\buy_button_click.9.png buy_button_click.9.png

set t=
@rem call :mycp ������Դ\button.png button.png
@rem call :mycp ������Դ\button.9.png button.9.png
@rem call :mycp ������Դ\button_click.png button_click.png
@rem call :mycp ������Դ\button_click.9.png button_click.9.png
call :mycp ������Դ\help.png help.png
call :mycp ������Դ\money.png money.png
@rem call :mycp ������Դ\payment_input.png payment_input.png
@rem call :mycp ������Դ\payment_input.9.png payment_input.9.png
call :mycp ������Դ\payment_input_2.9.png payment_input.9.png
@rem call :mycp ������Դ\recharge_input.png recharge_input.png
@rem call :mycp ������Դ\recharge_input.9.png recharge_input.9.png
call :mycp ������Դ\recharge_input_2.9.png recharge_input.9.png
@rem call :mycp ������Դ\tup_cft.png tup_cft.png
@rem call :mycp ������Դ\tup_dx.png tup_dx.png
@rem call :mycp ������Դ\tup_lt.png tup_lt.png
@rem call :mycp ������Դ\tup_sjdx.png tup_sjdx.png
@rem call :mycp ������Դ\tup_yd.png tup_yd.png
@rem call :mycp ������Դ\tup_yl.png tup_yl.png
@rem call :mycp ������Դ\tup_zfb.png tup_zfb.png
@rem call :mycp ������Դ\tup_zyb.png tup_zyb.png
@rem call :mycp ������Դ\zf_wxz.9.png zf_wxz.9.png
call :mycp ������Դ\zf_wxz_2.9.png zf_wxz.9.png
@rem call :mycp ������Դ\zf_xz.9.png zf_xz.9.png
call :mycp ������Դ\zf_xz_2.9.png zf_xz.9.png

set s=x:\resource\1029�Ż���drawable\drawable\
set p=%~dp0\..\assets\zz_res\drawable\
set pr=


:tup
set s=x:\resource\��ֵ��ʽСͼ��1029\֧��ͼ��_android\
set p=%~dp0\..\assets\zz_res\drawable\
set pr=cc_
set t=
@rem call :mycp tup_cft.png tup_cft.png
@rem call :mycp tup_dezf.png tup_dezf.png
@rem call :mycp tup_dx.png tup_dx.png
@rem call :mycp tup_lt.png tup_lt.png
@rem call :mycp tup_sjdx.png tup_sjdx.png
@rem call :mycp tup_yd.png tup_yd.png
@rem call :mycp tup_yl.png tup_yl.png
@rem call :mycp tup_zfb.png tup_zfb.png
call :mycp tup_zyb.png tup_zyb.png


:login
set s=x:\resource\SDK��½android\
set p=%~dp0\..\assets\zz_res\drawable\
set pr=login_
@rem call :mycp butten_lv.9.png button_lv.9.png
@rem call :mycp butten_lan.9.png button_lan.9.png
@rem call :mycp butten_hui.9.png button_hui.9.png
@rem call :mycp butten_huang.9.png button_huang.9.png
@rem call :mycp butten_@lv.9.png button_lv_click.9.png
@rem call :mycp butten_@lan.9.png button_lan_click.9.png
@rem call :mycp butten_@hui.9.png button_hui_click.9.png
@rem call :mycp butten_@huang.9.png button_huang_click.9.png
call :mycp butten_kuai.png button_kuai.png
call :mycp butten_kuai_anxia.png button_kuai_anxia.png

set s=x:\resource\1029�Ż���drawable\drawable\
set p=%~dp0\..\assets\zz_res\drawable\
set pr=
call :mycp login_button_huang.9.png login_button_huang.9.png
call :mycp login_button_huang_click.9.png login_button_huang_click.9.png
call :mycp login_button_hui.9.png login_button_hui.9.png
call :mycp login_button_hui_click.9.png login_button_hui_click.9.png
call :mycp login_button_lan.9.png login_button_lan.9.png
call :mycp login_button_lan_click.9.png login_button_lan_click.9.png
call :mycp login_button_lv.9.png login_button_lv.9.png
call :mycp login_button_lv_click.9.png login_button_lv_click.9.png
@rem call :mycp login_edit_press.9.png login_edit_press.9.png
call :mycp login_edit_press_2.9.png login_edit_press.9.png

:new_recharge_20131114
set s=x:\resource\��ֵ\
set p=%~dp0\..\assets\zz_res\drawable\
set pr=cc_
set t=
@rem call :mycp �Ƹ�ͨ.png tup_cft.png 
@rem call :mycp ���֧��.png tup_dezf.png
@rem call :mycp �й�����.png tup_dx.png
@rem call :mycp ��ͨ.png tup_lt.png
@rem call :mycp ����.png tup_sjdx.png
@rem call :mycp �й��ƶ�.png tup_yd.png
@rem call :mycp ����.png tup_yl.png
@rem call :mycp ֧���� .png tup_zfb.png
call :mycp ��.png money.png
call :mycp ��ǩ��.9.png title_bg.9.png
call :mycp ����.png bt_back.png
call :mycp ���ص��.png bt_back_click.png
call :mycp ѡ��.png charge_pull.png
call :mycp ѡ����.png charge_pull_click.png
call :mycp �����.9.png input_bg.9.png
call :mycp �װ�-9patch.9.png panel.9.png
call :mycp ѡ��.9.png paylist_sel.9.png
call :mycp �������ѡ��.9.png cand_sel.9.png
call :mycp ����1.9.png paylist_nor.9.png
@rem call :mycp button\ȷ�ϳ�ֵ.9.png button.9.png
@rem call :mycp button\ȷ�ϳ�ֵ���.9.png button_click.9.png
call :mycp ��\ȷ�ϳ�ֵ.9.png button.9.png
call :mycp ��\ȷ�ϳ�ֵ���.9.png button_click.9.png

call :mycp ͼ��\�Ƹ�ͨ.png tup_cft.png 
call :mycp ͼ��\���֧��.png tup_dezf.png
call :mycp ͼ��\�й�����.png tup_dx.png
call :mycp ͼ��\��ͨ.png tup_lt.png
call :mycp ͼ��\����.png tup_sjdx.png
call :mycp ͼ��\�й��ƶ�.png tup_yd.png
call :mycp ͼ��\����.png tup_yl.png
call :mycp ͼ��\֧����.png tup_zfb.png

@pause
goto :eof

:myCp
set ss=%s%%1
set st=%p%%pr%%t%%2
if "%st:~-6%"==".9.png" (
   @rem echo ��ҪԤ���룡 %ss%
   copy %ss% %st%
) else (
   copy %ss% %st%
)
goto :eof

:myRm
set ss=%s%%1
set st=%p%%pr%%t%%2
echo ɾ�� %st%
del %st%
goto :eof
