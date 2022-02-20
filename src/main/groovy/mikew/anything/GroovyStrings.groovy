package mikew.anything

assert 'hello'.tr('aeiou', 'AEIOU') == 'hEllO'

assert 'rome.wasnt.built.in.a.day'.tr( '.', ' ' ) == 'rome wasnt built in a day'