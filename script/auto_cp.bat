@echo off

@rem ��Դ·��
set s=x:\resource\android��ֵ��ͼ��˵���ĵ�\android��ֵ��ͼ\

@rem Ŀ��·��
set p=y:\workspace\android\zzsdk\assets\zz_res\drawable\

@rem ǰ׺
set pr=cc_

@rem ����
set t=
call :mycp ��ֵ���ģ�������ߣ�\recharge_ban.png recharge_ban.png
call :mycp ��ֵ���ģ��������룩\��ֵ_09.png charge_pull.png

set t=
call :myRm �һ��б�\ex_button.png ex_button.png
call :mycp �һ��б�\ex_button.9.png ex_button.9.png
call :myRm �һ��б�\ex_button_click.png ex_button_click.png
call :mycp �һ��б�\ex_button_click.9.png ex_button_click.9.png
call :mycp �һ��б�\ex_Right.png ex_right.png
call :mycp �һ��б�\ex_Right_click.png ex_right_click.png

set t=
@rem call :mycp �һ�����\Background.png background.png
call :mycp �һ�����\Background.9.png background.9.png
call :myRm �һ�����\buy_button.png buy_button.png
call :myRm �һ�����\buy_button_click.png buy_button_click.png
call :mycp �һ�����\buy_button.9.png buy_button.9.png
call :mycp �һ�����\buy_button_click.9.png buy_button_click.9.png

set t=
@rem call :mycp ������Դ\button.png button.png
call :mycp ������Դ\button.9.png button.9.png
@rem call :mycp ������Դ\button_click.png button_click.png
call :mycp ������Դ\button_click.9.png button_click.9.png
call :mycp ������Դ\help.png help.png
call :mycp ������Դ\money.png money.png
@rem call :mycp ������Դ\payment_input.png payment_input.png
call :mycp ������Դ\payment_input.9.png payment_input.9.png
@rem call :mycp ������Դ\recharge_input.png recharge_input.png
call :mycp ������Դ\recharge_input.9.png recharge_input.9.png
call :mycp ������Դ\tup_cft.png tup_cft.png
call :mycp ������Դ\tup_dx.png tup_dx.png
call :mycp ������Դ\tup_lt.png tup_lt.png
call :mycp ������Դ\tup_sjdx.png tup_sjdx.png
call :mycp ������Դ\tup_yd.png tup_yd.png
call :mycp ������Դ\tup_yl.png tup_yl.png
call :mycp ������Դ\tup_zfb.png tup_zfb.png
call :mycp ������Դ\tup_zyb.png tup_zyb.png
call :mycp ������Դ\zf_wxz.9.png zf_wxz.9.png
call :mycp ������Դ\zf_xz.9.png zf_xz.9.png

@pause
goto :eof

:myCp
set ss=%s%%1
set st=%p%%pr%%t%%2
if "%st:~-6%"==".9.png" (
    echo ��ҪԤ���룡 %ss%
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
