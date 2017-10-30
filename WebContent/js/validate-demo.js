charset="UTF-8";
$(function() {
    function ratingEnable() {
        var times=60,
            timer=null;

        $("#send").click(function () {
            timer = setInterval(djs, 1000);// ��ʱ��ʼ
        });
        function djs(){
            send.value = times+"�������";
            send.setAttribute('disabled','disabled');
            send.style.background='#ddd';
            send.style.border='1px solid #ccc';
            times--;
            if(times <= 0){
                send.value = "������֤��";
                send.style.background='#BCC6D1';
                send.removeAttribute('disabled');
                times = 60;
                clearInterval(timer);
            }
        }
        //jquery.validate����֤
        $(document).ready(function(){
            //ע�����֤
            $("#registerForm").validate({
                rules:{
                    username:{
                        required:true,//����
                        minlength:2, //����6���ַ�
                        maxlength:32//���20���ַ�
                    },
                    password:{
                        required:true,
                        minlength:3,
                        maxlength:32
                    },
                    email:{
                        required:true,
                        email:true
                    },
                    confirm_password:{
                        required:true,
                        minlength:3,
                        equalTo:'.password'
                    },
                    phone_number:{
                        required:true,
                        phone_number:true,//�Զ���Ĺ���
                        digits:true//����
                    },
                    photo_code:{
                        required:true,
                        minlength:4,
                        maxlength:6
                    }
                },
                //������Ϣ��ʾ
                messages:{
                    username:{
                        required:"������д�û���",
                        minlength:"�û�������Ϊ2���ַ�",
                        maxlength:"�û�������Ϊ32���ַ�"
//                            remote: "�û����Ѵ���"
                    },
                    password:{
                        required:"������д����",
                        minlength:"��������Ϊ3���ַ�",
                        maxlength:"��������Ϊ32���ַ�"
                    },
                    email:{
                        required:"�����������ַ",
                        email: "��������ȷ��email��ַ"
                    },
                    confirm_password:{
                        required: "���ٴ���������",
                        minlength: "ȷ�����벻������3���ַ�",
                        equalTo: "�����������벻һ��"//����һ��Ԫ����ͬ
                    },
                    phone_number:{
                        required:"�������ֻ�����",
                        digits:"��������ȷ���ֻ�����"
                    },
                    photo_code:{
                        required:"��������ȷ�Ķ�ά��",
                        minlength:"��ά������Ϊ4������",
                        maxlength:"��ά������Ϊ6���ַ�"
                    }
                }
            });
            //����Զ�����֤����
            jQuery.validator.addMethod("phone_number", function(value, element) {
                var length = value.length;
                var phone_number = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                return this.optional(element) || (length == 11 && phone_number.test(value));
            }, "�ֻ������ʽ����");
        });
    }
    ratingEnable();
});
