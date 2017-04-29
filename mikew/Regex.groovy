def str = '''this is a start <g:message code="deployment.order.label" default="Definition order" />
dddfg fgfr
${message(code: "no.using.xx.label", default: 'Start')}"
this is a usf
<g:actionSubmit class="start" id="start"
value="${message(code: 'default.button.start.label', default: 'Start')}" action="start"/>
kkkkk'''

def group = ( str =~ /(?<=code[=:]\s?["'])([\w.]+)(?=["'])/)

assert 3 == group.count
assert 'deployment.order.label' == group[0][0]
assert 'no.using.xx.label' == group[1][0]
assert 'default.button.start.label' == group[2][0]